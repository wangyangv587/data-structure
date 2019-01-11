package datastructure.hash;

/**
 * @author: Alex
 * @date: 2018/12/21 14:45
 * description:
 */
public class Student {

    int grade;
    int cls;
    String firstName;
    String lastName;

    @Override
    public int hashCode() {

        int b = 31;

        int hash = 0;
        hash = hash * b + grade;
        hash = hash * b + cls;
        hash = hash * b + firstName.toLowerCase().hashCode();
        hash = hash * b + lastName.toLowerCase().hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Student another = (Student) o;

        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
    }
}
