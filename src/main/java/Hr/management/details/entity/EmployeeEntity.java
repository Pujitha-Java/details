package Hr.management.details.entity;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
import jakarta.persistence.Id;
    import jakarta.persistence.Table;

    @Entity
    @Table
    public class EmployeeEntity {

        @Id
        @Column(name="id")
        private int id;
        @Column(name="name")
        private String name;
        @Column(name="age")
        private int age;
        @Column(name="organization")
        private String organization;
        @Column(name="type")
        private String type;
        @Column(name="experience")
        private float experience;


        public EmployeeEntity() {
        }

        public EmployeeEntity(int id, String name, int age, String organization,String type,float experience) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.organization= organization;
            this.type=type;
            this.experience=experience;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;

        }
        public String getName() {

            return name;
        }

        public void setName(String name) {

            this.name = name;
        }

        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }

        public String getOrganization() {

            return organization;
        }

        public void setOrganization(String organization) {

            this.organization = organization;
        }
        public String getType(){

            return type;
        }
        public void setType(String type){
            this.type=type;

        }

        public float getExperience() {
            return experience;
        }

        public void setExperience(float experience) {
            this.experience = experience;
        }
    }

