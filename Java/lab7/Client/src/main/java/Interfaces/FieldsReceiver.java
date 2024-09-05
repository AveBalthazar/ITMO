package Interfaces;

import data.Coordinates;
import data.FormOfEducation;
import data.Person;
import data.Semester;

public interface FieldsReceiver {

    /**
     * Метод для получения поля name
     */
    String getName();

    /**
     * Метод для получения поля coordinates
     */
    Coordinates getCoordinates();

    /**
     * Метод для получения поля students count
     */
    Integer getStudentsCount();

    /**
     * Метод для получения поля average mark
     */
    Double getAverageMark();

    /**
     * Метод для получения поля form of education
     */
    FormOfEducation getFormOfEducation();

    /**
     * Метод для получения поля semester
     */
    Semester getSemester();

    /**
     * Метод для получения поля admin
     */
    Person getGroupAdmin();
}