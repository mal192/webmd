package com.example.webmd;
import javax.persistence.*;

@Entity //Сообщаем Hibernate класс таблицы
public class MSG {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;

    //V4
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") // название поля
    private User autor;
    // класс MSG зоздает базу под данную структуру
    public MSG() { //конструктор без параметра обязательное условие
    }
    //запускаем генератор конструкторов alt+insert -> и выбираем Constuctor и выбрали text и tag
    public MSG(String text, String tag, User user) { //User правка V4
        this.autor = user;  // <- V4
        this.text = text;
        this.tag = tag;
    }
    // V4 _ для проверки отстуствия у сообщения пользоателя
    public  String getAutorName(){
// как я понял это тоже что и if (autor!=null) return "<none>"; else return autor.getUsername();
        return autor!=null ? autor.getUsername() : "<none>";
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

    //v4
    public User getAutor() {  return autor;   }
    public void setAutor(User autor) { this.autor = autor;   }
}
