package com.example.barangayservicehub.getter_class;

public class Get_Programs {

    private String serviceName, serviceDescription;

    public Get_Programs(){

    }

    public Get_Programs(String serviceName, String serviceDescription) {
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
