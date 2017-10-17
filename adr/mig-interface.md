# Implementation of interface to [healthcare gateway's](http://healthcaregateway.co.uk/) medical information gateway

## Introduction
As of 06/10/2017 the decision has been made to use healthcare gateway's Medical Information Gateway (MIG) to retrieve 
patient information from EMIS and Vision. MIG provides a [SOAP](https://en.wikipedia.org/wiki/SOAP) based interface to retrieve 
patient medical records. It primarily acts as a proxy between the client application and the data source (EMIS or Vision).

## Detail

The following diagram illustrates the overall architecture of the system:
![solution architecture](./img/sa-iii.png)

Thus primary reason for the JUYI project is to retrieve medical information from remote systems. Three mechanisms are 
used to achieve this: Real time queries, data feeds and push feeds. The MIG interface provides a real time interface to 
EMIS and Vision.

The product team have implemented an integration engine which is responsible for initiating calls to both internal
and external data sources. An ODP for the [integration engine](https://kainos-evolve.atlassian.net/wiki/spaces/STG/pages/112464040/ODP+10+-+Integration+Services+-+separate+delivery+from+product)
has been created and reviewed.

In agreement with the product team a connector service has been created that handles calls to outgoing systems and, when
required, transforms the data returned into [FHIR](http://hl7.org/fhir/index.html), the standard data format of the Evolve product.
The connector framework uses [dropwizard](http://www.dropwizard.io/1.2.0/docs/) to implement a web service through which 
all external requests are made.

### MIG Implementation Detail

The following diagram illustrates the sequence of messages passed between JUYI and the MIG:

![mig flow](./img/mig-01.png)

### EMIS vs Vision responses

As can be seen from the diagram above we only need to make one call to EMIS to retrieve the patient record, 
but we need to make up to 10 calls to Vision to retrieve the same information. This is due to a restriction 
on the data sets that each organization exposes. In the test environment the call to EMIS to retrieve the ful
patient record takes 4 seconds, whilst a call to Vision to retrieve a specific data set takes on average 2 seconds.
This means that accessing the full patient record sequentially will take in the region of 20 seconds.

In an attempt to reduce the overall time taken a multi threaded approach is being considered whereby all of the 
Vision calls are made concurrently. The [Java 8 concurrency](https://docs.oracle.com/javase/8/docs/technotes/guides/concurrency/changes8.html)
features will be utilized to implement the required concurrent requests.

### Concurrent requests

In order to facilitate concurrent requests to the MIG a thread pool will be initialized on application startup.
The size of the thread pool will be configurable and determined by a parameter retrieved from the database. Each
call to the MIG and the transformation to FHIR will be carried out by an instance of a transformation task class.
The output of each transformation will be consolidated and returned to the integration engine as a FHIR bundle.

### XML parsing

Currently the responses retreived from the MIG are unmarshalled to Java objects using [JAXB](https://en.wikipedia.org/wiki/Java_Architecture_for_XML_Binding). 
Calls to the MIG endpoint are implemented via an [Apache CXF](http://cxf.apache.org/) client. The transformers convert the XML
to HIR Json using [Jackson](https://github.com/FasterXML/jackson) libraries that form part of Dropwizard.

Several different mechanisms are available for unmarshalling XML, with different performance charcteristics. 
The proposed approach is DOM based, which loads the whole XML structure into memory as Java objects. This method is 
suitable for processing small to medium sized payloads, but may result in performance problems with larger payloads. Should performance
issues occur the approach will be adjusted to use a Sax based parser, which traverses the structure and processes it section by 
section.

### Database lookups

As part of the transformation process reference data lookups will be required. Reference data lookups will be required for:
* identifying excluded codes(in the region of 2,000 codes)
* acquiring the required text for repurposed codes (total number not known, but expected to be in the hundreds)
In order to process the incoming messages as quickly as possible the required reference data will be loaded into suitable cache data structures. 
The data structures will be populated during application startup. The [JOOQ](https://www.jooq.org/) library is already used within the product to 
simplify the process of returning data from the database.

#### Scheduled updates

It is anticipated that the existing staging database will be used to store the required reference data. The reference data will be loaded on application startup
and subsequently updated using a scheduled [Quartz](http://www.quartz-scheduler.org/) task.

### Technical risks in the solution

* The multithreaded approach required for MIG calls requires careful monitoring and tuning to ensure that the use of too many threads 
results in excessive context switching and associated loss of performance. The host machines for the service have 2 CPUs and 8 GB or RAM. Load testing of the service is required 
to validate the performance characteristics of the solution and to establish the number of load balanced virtual machines required to meet the non functional requirement of 
300 concurrent users.
* The MIG API requires that a consent to view indicator is included in the request message, together with the name of the user who initiated the call. 
This approach conflicts with the current Evolve approach of making the data requests while the permission to view screen is being displayed. 