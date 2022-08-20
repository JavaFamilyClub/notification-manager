package club.javafamily.nf.properties;

import lombok.Data;

import java.io.Serializable;
import java.time.Duration;

@Data
public class Inhibit implements Serializable {

    /**
     * enabled
     */
    private Boolean enabled = false;

    /**
     * Entry expiration. By default the entries never expire.
     */
    private Duration ttl;
}
