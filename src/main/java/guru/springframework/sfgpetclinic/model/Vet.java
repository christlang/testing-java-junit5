package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Vet extends Person {

    private Set<Speciality> specialities = new HashSet<>();

    public Vet(Long id, String firstName, String lastName, Set<Speciality> specialities) {
        super(id, firstName, lastName);
        this.specialities = specialities;
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (this == o) return true;
        if (!(o instanceof Vet)) return false;
        Vet vet = (Vet) o;
        return Objects.equals(specialities, vet.specialities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialities);
    }

    @Override
    public String toString() {
        return "Vet{" +
                "id='" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", specialities=" + specialities +
                '}';
    }
}
