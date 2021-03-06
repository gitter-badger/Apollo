package org.bbop.apollo

/**
 * Converted
 * Chado?
 */
class Feature implements Ontological{

    static auditable = true

    static constraints = {
        name nullable: false
        uniqueName nullable: false
        dbxref nullable: true
        sequenceLength nullable: true
        md5checksum nullable: true
        isAnalysis nullable: true
        isObsolete nullable: true
        dateCreated nullable: true
        lastUpdated nullable: true
        symbol nullable: true // TODO: should be false and unique per organism
        description nullable: true
        status nullable: true
    }

    String symbol
    String description
    DBXref dbxref;
    String name;
    String uniqueName;
    Integer sequenceLength;
    String md5checksum;
    Status status
    boolean isAnalysis;
    boolean isObsolete;

    Date dateCreated;
    Date lastUpdated ;
//    Feature owner
//    Date timeAccessioned;
//    Date timeLastModified;

    static hasMany = [
            featureLocations: FeatureLocation
            ,featureGenotypes: FeatureGenotype
            ,parentFeatureRelationships: FeatureRelationship  // relationships where I am the parent feature relationship
            ,childFeatureRelationships: FeatureRelationship // relationships where I am the child feature relationship
            ,featureCVTerms: FeatureCVTerm
            ,featureSynonyms: FeatureSynonym
            ,featureDBXrefs: DBXref
            ,featurePublications: Publication
            ,featurePhenotypes: Phenotype
            ,featureProperties: FeatureProperty
            ,synonyms: Synonym
            ,owners:User
    ]

    static mappedBy = [
            parentFeatureRelationships: 'parentFeature'
            ,childFeatureRelationships: 'childFeature'
            ,featureGenotypes: "feature"
            ,featureLocations: "feature"
    ]
    
    static mapping = {
        childFeatureRelationships cascade: 'all-delete-orphan'
        parentFeatureRelationships cascade: 'all-delete-orphan'
        featureLocations cascade: 'all-delete-orphan'
    }

    static belongsTo = [
            User
    ]
    
    public User getOwner(){
        if(owners?.size()>0){
            return owners.iterator().next()
        }
        return null
    }


    public boolean equals(Object other) {
//        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof Feature) ) return false;
        Feature castOther = ( Feature ) other;

        return  (this?.ontologyId==castOther?.ontologyId) \
                   &&  (this?.getUniqueName()==castOther?.getUniqueName())
    }

    public int hashCode() {
        int result = 17;


        result = 37 * result + ( ontologyId == null ? 0 : this.ontologyId.hashCode() );

//        result = 37 * result + (() == null ? 0 : this.getOrganism().hashCode() );

        result = 37 * result + ( getUniqueName() == null ? 0 : this.getUniqueName().hashCode() );



        return result;
    }

    public Feature generateClone() {
//        Feature cloned = new Feature();
        Feature cloned = this.getClass().newInstance()
//        cloned.type = this.type;
        cloned.dbxref = this.dbxref;
//        cloned.organism = this.organism;
        cloned.name = this.name;
        cloned.uniqueName = this.uniqueName;
        cloned.sequenceLength = this.sequenceLength;
        cloned.md5checksum = this.md5checksum;
        cloned.isAnalysis = this.isAnalysis;
        cloned.isObsolete = this.isObsolete;
        cloned.dateCreated = this.dateCreated;
        cloned.lastUpdated = this.lastUpdated;
        cloned.featureLocations = this.featureLocations;
        cloned.featureGenotypes = this.featureGenotypes;
        cloned.parentFeatureRelationships = this.parentFeatureRelationships;
        cloned.childFeatureRelationships = this.childFeatureRelationships;
        cloned.featureCVTerms = this.featureCVTerms;
        cloned.featureSynonyms = this.featureSynonyms;
        cloned.featureDBXrefs = this.featureDBXrefs;
        cloned.featurePublications = this.featurePublications;
        cloned.featurePhenotypes = this.featurePhenotypes;
        cloned.featureProperties = this.featureProperties;
        return cloned;
    }



//    @Override
//    public String toString() {
//        return String.format("%s (%s)", getUniqueName(), ontologyId);
//    }



    /** Convenience method for retrieving the location.  Assumes that it only contains a single
     *  location so it returns the first (and hopefully only) location from the collection of
     *  locations.  Returns <code>null</code> if none are found.
     *
     *  @deprecated  Just use getFeatureLocation
     *
     * @return FeatureLocation of this object
     */
    public FeatureLocation getSingleFeatureLocation() {
        Collection<FeatureLocation> locs = getFeatureLocations();
        if (locs != null) {
            Iterator<FeatureLocation> i = locs.iterator();
            if (i.hasNext()) {
                return i.next();
            }
        }
        return null;
    }


    /** Convenience method for setting the location.  Assumes that it only contains a single
     *  location so the previous location will be removed.
     *
     *  @param featureLocation - new FeatureLocation to set this gene to
     */
    public void setOnlyFeatureLocation(FeatureLocation featureLocation) {
        Collection<FeatureLocation> locs = getFeatureLocations();
        if (locs != null) {
            locs.clear();
        }
        featureLocations.add(featureLocation)
//        feature.addFeatureLocation(featureLocation);
    }


    /** Convenience method for retrieving the location.  Assumes that it only contains a single
     *  location so it returns the first (and hopefully only) location from the collection of
     *  locations.  Returns <code>null</code> if none are found.
     *
     * @return FeatureLocation of this object
     */
    public FeatureLocation getFeatureLocation() {
        Collection<FeatureLocation> locs = getFeatureLocations();
        if (locs != null) {
            Iterator<FeatureLocation> i = locs.iterator();
            if (i.hasNext()) {
                return i.next();
            }
        }
        return null;
    }


    /** Get the length of this feature.
     *
     * @return Length of feature
     */
    public int getLength() {
        return getFeatureLocation().getFmax() - getFeatureLocation().getFmin();
    }

    public Integer getFmin(){
        featureLocation.fmin
    }

    public Integer getFmax(){
        featureLocation.fmax
    }

    public Integer getStrand(){
        featureLocation.strand
    }


    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", uniqueName='" + uniqueName + '\'' +
                ", sequenceLength=" + sequenceLength +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
