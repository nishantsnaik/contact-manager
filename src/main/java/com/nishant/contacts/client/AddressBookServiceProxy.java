package com.nishant.contacts.client;

import com.nishant.contacts.dto.Contact;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.eureka.ConditionalOnRibbonAndEurekaEnabled;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
//@FeignClient(name = "address-book", url = "localhost:8090")
//no need to specify the url, as the app will talk to eureka, and get the ports using name
//@FeignClient(name = "address-book")
//if app is talking through zuul, replace the name with zuul server name, and update the URI with the application name
@FeignClient(name = "zuul-api-gateway")
@RibbonClient(name = "address-book")
public interface AddressBookServiceProxy {

    @GetMapping("address-book/api/contacts/{contactId}/addresses")
    public Object findAddress(@PathVariable("contactId") Integer contactId);
}
