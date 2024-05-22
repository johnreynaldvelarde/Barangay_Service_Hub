package com.example.barangayservicehub.getter_class;

public class Get_Services {

    private String serviceName, serviceDescription;

    public Get_Services(){

    }

    public Get_Services(String serviceName, String serviceDescription) {
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

}
