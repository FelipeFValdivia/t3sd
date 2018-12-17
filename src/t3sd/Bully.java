/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t3sd;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author felipefvaldivia
 */
public class Bully {
    private Long id;
    private Long leaderId;
    private String LeaderAddress;
    
    private Map<String, Long> address_best_sums = new HashMap<>();

    Bully(Long id, Long leaderId, String LeaderAddress, String myAddress, String address1, String address2, String address3) {
        this.id = id;
        this.leaderId = leaderId;
        this.LeaderAddress = LeaderAddress;
        this.address_best_sums.put(myAddress, id);
        this.address_best_sums.put(address1, null);
        this.address_best_sums.put(address2, null);
        this.address_best_sums.put(address3, null);
    }
    
    private Map<String, Long> getAddress(){
        return this.address_best_sums;
    }

    Long get_address_best_sum(String address) {
        return this.address_best_sums.get(address);
    }

    void set_address_best_sum(String address, Long sum) {
        this.address_best_sums.put(address, sum);
        
    }

    void set_leader(String address, Long best_sum) {
        this.LeaderAddress = address;
        this.id = best_sum;
    }

    String get_leader_address() {
        return this.LeaderAddress;
    }
    
    
    
    
}
