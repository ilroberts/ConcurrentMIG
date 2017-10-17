package com.kainos.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.spinscale.dropwizard.jobs.JobConfiguration;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class MIGConfiguration extends Configuration implements JobConfiguration {

    @NotEmpty
    private String template;
    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }


}
