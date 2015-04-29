package org.opensrp.service.reporting.rules;

import static org.opensrp.common.AllConstants.CommonFormFields.SERVICE_PROVIDED_PLACE;
import static org.opensrp.common.AllConstants.CommonFormFields.SUB_CENTER_SERVICE_PROVIDED_PLACE_VALUE;

import org.opensrp.util.SafeMap;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvidedAtSub_CenterRule implements IRule {
    @Override
    public boolean apply(SafeMap reportFields) {
        return reportFields.has(SERVICE_PROVIDED_PLACE)
                && SUB_CENTER_SERVICE_PROVIDED_PLACE_VALUE.equalsIgnoreCase(reportFields.get(SERVICE_PROVIDED_PLACE));
    }
}
