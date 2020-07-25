package App.dao.entity;

public enum Grade {

        A("Exellent"),
        B("Good"),
        C("Bad");

        private String title;

        Grade(String title){
            this.title=title;
        }

        public String getTitle(){
            return title;
        }
    }


