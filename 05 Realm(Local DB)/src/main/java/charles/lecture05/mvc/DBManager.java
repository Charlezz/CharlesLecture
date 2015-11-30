package charles.lecture05.mvc;

import charles.lecture05.PhoneBook;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Charles on 15. 11. 25..
 */
public class DBManager {
    private static DBManager instance = new DBManager();
    private Realm realm;

    public static DBManager getInstance() {
        return instance;
    }

    private DBManager() {
        realm = Realm.getDefaultInstance();
    }

    public void add(PhoneBook pb) {
        realm.beginTransaction();
        realm.copyToRealm(pb);
        realm.commitTransaction();
    }

    public void update(PhoneBook pb) {
        realm.beginTransaction();
        //있으면 업데이트, 없으면 새로 만듬
        realm.copyToRealmOrUpdate(pb);
        realm.commitTransaction();
    }

    public void delete(String name) {
        realm.beginTransaction();
        realm.where(PhoneBook.class).equalTo(PhoneBook.FIELD_NAME, name).findFirst().removeFromRealm();
        realm.commitTransaction();
    }

    public RealmResults<PhoneBook> getPhoneBookResult() {
        //조회
        return realm.where(PhoneBook.class).findAll();
    }

    public long generateId() {
        RealmResults<PhoneBook> results = getPhoneBookResult();
        if (results.size() == 0) {
            return 0;
        } else {
            return results.get(results.size()-1).getId() + 1;
        }

    }

   public PhoneBook getPhoneBook(long id) {
        return realm.where(PhoneBook.class).equalTo(PhoneBook.FIELD_ID, id).findFirst();
    }


    /**
     * 1. UUID.randomUUID():String형으로 고유 아이디 생성하는법. 100%아님 99.9999999....
     *
     * 2. 정수형으로 아이디를 관리하고 싶을때: 가장 마지막에 들어온 레코드를 조회해서 그 레코드에 해당 정수형 아이디에 +1을 하여서 아이디를 부여하는방식
     *
     * */


}
