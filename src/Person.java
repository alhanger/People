/**
 * Created by alhanger on 10/19/15.
 */
public class Person implements Comparable {
    String id;
    String firstName;
    String lastName;
    String email;
    String country;
    String ipAddress;

    public Person(String id, String firstName, String lastName, String email, String country, String ipAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.ipAddress = ipAddress;
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        int result = lastName.compareTo(p.lastName);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Contact %s %s at %s", firstName, lastName, email);
    }
}
