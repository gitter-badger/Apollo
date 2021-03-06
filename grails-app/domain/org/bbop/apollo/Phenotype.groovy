package org.bbop.apollo

/**
 * Converted
 * Chado?
 */
class Phenotype {

    static constraints = {
    }

//    Integer phenotypeId;
    CVTerm attribute;
    CVTerm cvalue;
    CVTerm assay;
    CVTerm observable;
    String uniqueName;
    String value;

    static hasMany = [
            phenotypeCVTerms : CVTerm
            ,phenotypeStatements: PhenotypeStatement
            ,features: Feature
    ]

    static belongsTo = [
            Feature
    ]


    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof Phenotype) ) return false;
        Phenotype castOther = ( Phenotype ) other;

        return ( (this.getUniqueName()==castOther.getUniqueName()) || ( this.getUniqueName()!=null && castOther.getUniqueName()!=null && this.getUniqueName().equals(castOther.getUniqueName()) ) );
    }

    public int hashCode() {
        int result = 17;


        result = 37 * result + ( getUniqueName() == null ? 0 : this.getUniqueName().hashCode() );


        return result;
    }

    public Phenotype generateClone() {
        Phenotype cloned = new Phenotype();
        cloned.attribute = this.attribute;
        cloned.cvalue = this.cvalue;
        cloned.assay = this.assay;
        cloned.observable = this.observable;
        cloned.uniqueName = this.uniqueName;
        cloned.value = this.value;
        cloned.phenotypeCVTerms = this.phenotypeCVTerms;
        cloned.phenotypeStatements = this.phenotypeStatements;
        cloned.features = this.features
        return cloned;
    }
}
