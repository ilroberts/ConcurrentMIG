# ConcurrentMIG
[![Build Status](https://travis-ci.org/ilroberts/ConcurrentMIG.png?branch=master)](https://travis-ci.org/ilroberts/ConcurrentMIG)

A demo project that serves as a proof of concept to validate concurrent calls to a [CXF](http://cxf.apache.org/) 
provided SOAP endpoint from a [dropwizard](http://www.dropwizard.io/1.2.0/docs/) web service.

Data is retrieved from the publicly available SOAP country information located at [webservicex.net](http://www.webservicex.com/new/Home/Index).

Concurrent calls are made using a standard [Executor Service](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html). 
The aim of the exercise was to ensure that no threading issues would be encountered with CXF when making
multiple concurrent calls. 

## Message structure

Use a POST request with the following body:

```
{
    "countries": [
        "United Kingdom",
        "Canada",
        "New Zealand",
        "Vietnam",
        "Sudan"
    ]
}
```

Should result in the following being returned:

```
[
    {
        "country": "United Kingdom",
        "currencyCode": "GBP",
        "currencyCodeDescription": "Pound Sterling"
    },
    {
        "country": "Canada",
        "currencyCode": "CAD",
        "currencyCodeDescription": "Canadian Dollar"
    },
    {
        "country": "New Zealand",
        "currencyCode": "NZD",
        "currencyCodeDescription": "New Zealand Dollar"
    },
    {
        "country": "Vietnam",
        "currencyCode": "VND",
        "currencyCodeDescription": "Vietnamese Dong"
    },
    {
        "country": "Sudan",
        "currencyCode": "SDD",
        "currencyCodeDescription": "Sudanese Dollar"
    }
]
```