package Hr.management.details.entity;

    import Hr.management.details.model.EmployeeType;
    import jakarta.persistence.*;

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
        @Enumerated(EnumType.STRING)
        @Column(name="type")
        private EmployeeType type;
        @Column(name="experience")
        private float experience;


        public EmployeeEntity() {
        }

        public EmployeeEntity(int id, String name, int age,EmployeeType type,String organization,float experience) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.type=type;
            this.organization= organization;
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
        public EmployeeType getType(){

            return type;
        }
        public void setType(EmployeeType type){
            this.type=type;

        }

        public float getExperience() {
            return experience;
        }

        public void setExperience(float experience) {
            this.experience = experience;
        }
    }

