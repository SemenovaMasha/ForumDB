
package EntitiesClasses;

/**
 *
 * @author Masha
 */
public class Section {

    private int id;
    private String name;
    private String dateOfCreation;

    public Section(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.dateOfCreation = date;
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dateOfCreation
     */
    public String getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * @param dateOfCreation the dateOfCreation to set
     */
    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
