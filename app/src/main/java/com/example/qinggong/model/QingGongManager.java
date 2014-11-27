package com.example.qinggong.model;

/**
 * Created by ZJGJK03 on 2014/11/26.
 */
public class QingGongManager {
    QingGongEntity qingGongEntity=new QingGongEntity();
    QingGongEntityEx qingGongEntityEx=new QingGongEntityEx();

    public QingGongManager() {
    }

    public QingGongManager(QingGongEntity qingGongEntity, QingGongEntityEx qingGongEntityEx) {
        this.qingGongEntity = qingGongEntity;
        this.qingGongEntityEx = qingGongEntityEx;
    }

    public QingGongEntity getQingGongEntity() {
        return qingGongEntity;
    }

    public void setQingGongEntity(QingGongEntity qingGongEntity) {
        this.qingGongEntity = qingGongEntity;
    }

    public QingGongEntityEx getQingGongEntityEx() {
        return qingGongEntityEx;
    }

    public void setQingGongEntityEx(QingGongEntityEx qingGongEntityEx) {
        this.qingGongEntityEx = qingGongEntityEx;
    }
}
