package projet.studenity.model;

import javax.persistence.*;

@Entity
@Table(name="NOTIFICATION")
public class Notification {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="id_User")
    private int idUser;
    @Column(name="id_Tempo")
    private int idTempo;
    @Column(name="Type_Noti")
    private int typeNoti;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTempo() {
        return idTempo;
    }

    public void setIdTempo(int idTempo) {
        this.idTempo = idTempo;
    }

    public int getTypeNoti() {
        return typeNoti;
    }

    public void setTypeNoti(int typeNoti) {
        this.typeNoti = typeNoti;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
