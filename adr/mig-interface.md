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

### Database lookups

## Summary

### Technical risks in the solution