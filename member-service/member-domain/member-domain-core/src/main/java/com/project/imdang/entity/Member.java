package com.project.imdang.entity;

import com.project.imdang.valueobject.Gender;
import com.project.imdang.valueobject.MemberId;

public class Member {

    private MemberId id;

//    private String name;
    private String nickname;
    private String birthDate;
    private Gender gender;

    private String image;

//    private Password password;
//    private String introduce;

//    private List<InsightId> insightIds;
//    private int insightCount;

    private int exchangeCount;

//    private int visitCount;
//    private int totalVisitCount;

/*
    public Member(String name, String image, String password, String introduce) {
        setName(name);
        setImage(image);
        setPassword(password);
        setIntroduce(introduce);
    }*/

    // TODO - 동시성 체크
    // member.addVisitCount();
//    public void addVisitCount() {
//        this.visitCount++;
//        this.totalVisitCount++;
//    }

/*
    private void setName(String name) {
        if (name.length() < 1) {
            throw new NameNotEnoughLengthException();
        }
        this.name = name;
    }*/

    private void setImage(String image) {
        this.image = image;
    }

//    private void setPassword(String password) {
//        this.password = new Password(password);
//    }

//    private void setIntroduce(String introduce) {
//        this.introduce = introduce;
//    }
/*
    public void initializePassword() {
        String newPassword = generateRandomPassword();
        this.password = new Password(newPassword);
        // TODO
//        Events.raise(new PasswordChangedEvent(id.getId(), newPassword));
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!password.match(oldPassword)) {
            throw new PasswordNotMatchException();
        }
        this.password = new Password(newPassword);
        // TODO
//        Events.raise(new PasswordChangedEvent(id.getId(), newPassword));
    }

    private String generateRandomPassword() {
        Random random = new Random();
        int number = random.nextInt();
        return Integer.toHexString(number);
    }*/
}
