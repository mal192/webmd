package com.example.webmd;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //Сообщаем Hibernate класс таблицы
public class MSG {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;
    // класс MSG зоздает базу под данную структуру
    public MSG() { //конструктор без параметра обязательное условие
    }
    //запускаем генератор конструкторов alt+insert -> и выбираем Constuctor и выбрали text и tag
    public MSG(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }
    // для ускарения генерируем alt+insert -> Getter and Setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
