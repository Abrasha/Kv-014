package edu.softserve.zoo.persistence.specification.hibernate.impl.house;

import edu.softserve.zoo.core.exceptions.ApplicationException;
import edu.softserve.zoo.core.exceptions.persistence.PersistenceException;
import edu.softserve.zoo.core.model.House;
import edu.softserve.zoo.core.model.ZooZone;
import edu.softserve.zoo.core.util.Validator;
import edu.softserve.zoo.persistence.exception.PersistenceReason;
import edu.softserve.zoo.persistence.specification.Specification;
import edu.softserve.zoo.persistence.specification.hibernate.DetachedCriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 * Implementation of {@link Specification} for retrieving houses by specific {@link ZooZone} id
 *
 * @author Vadym Holub
 */
public class GetAllByZooZoneIdSpecification implements DetachedCriteriaSpecification<House> {
    private static final String ZOO_ZONE_ID_FIELD = "zone.id";
    private final Long zooZoneId;

    public GetAllByZooZoneIdSpecification(Long zooZoneId) {
        this.zooZoneId = zooZoneId;
    }

    @Override
    public DetachedCriteria query() {
        Validator.notNull(zooZoneId, ApplicationException.getBuilderFor(PersistenceException.class)
                .forReason(PersistenceReason.NULL_VALUE_IN_SPECIFICATION)
                .withMessage("cannot perform " + this.getClass().getSimpleName() + " with null id")
                .build());
        return DetachedCriteria.forClass(House.class).add(Restrictions.eq(ZOO_ZONE_ID_FIELD, zooZoneId));
    }
}
