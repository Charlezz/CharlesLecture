package charles.lecture05;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Charles on 15. 11. 25..
 */
public class PhoneBook extends RealmObject {
    //필수: RealmObject 상속
    //선택: 어노테이션 설정, 문서참조

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_NUMBER = "number";

    @PrimaryKey
    private long id;
    private String name;
    private int number;


    //필수: 게터 세터 설정


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
