package database;

/**
 * Enum, хранящий запросы к базе
 */
public enum Statements {

    addStudyGroup("INSERT INTO s408624StudyGroups " +
            "(id, name, xCoordinate, yCoordinate, studentsCount, averageMark, formOfEducation, " +
            "semesterEnum, groupAdminName, groupAdminWeight, groupAdminHairColor, author) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),

    generateId("SELECT nextval('ids')"),

    addUserWithPassword("INSERT INTO s408624Users (username, hashPassword) VALUES(?, ?)"),

    checkUser("SELECT * FROM s408624Users WHERE username=? AND hashPassword=?"),

    updateStudyGroup("UPDATE s408624StudyGroups SET " +
            "name=?, xCoordinate=?, yCoordinate=?, studentsCount=?, averageMark=?, formOfEducation=?, " +
            "semesterEnum=?, groupAdminName=?, groupAdminWeight=?, groupAdminHairColor=? " +
            "WHERE id = ?"),

    getById("SELECT * FROM s408624StudyGroups WHERE id = ?"),

    deleteById("DELETE FROM s408624StudyGroups WHERE id = ?"),

    clearAllByUser("DELETE FROM s408624StudyGroups WHERE author = ?"),

    takeAll("SELECT * FROM s408624StudyGroups");

    private final String statement;

    Statements(String aStatement) {
        statement = aStatement;
    }

    public String getStatement() {
        return statement;
    }
}
