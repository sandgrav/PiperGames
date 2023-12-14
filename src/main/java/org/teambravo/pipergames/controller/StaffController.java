package org.teambravo.pipergames.controller;

import org.teambravo.pipergames.entity.Staff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class StaffController {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    // CRUD

    //CREATE
    // Ska man kunna lägga till staff? Tänker att de kopplas direkt med foreign_keys
    public boolean save(Staff staff) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(staff);
            transaction.commit();
            return true;
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;

    }

    //READ - Läsa av staff id och person id
    public List<Staff> getAllStaff (boolean printOut){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            List<Staff> staffResultList = entityManager.createQuery("FROM Staff ", Staff.class).getResultList();
            // teamClassToReturn.addAll(teamResultList.getResultList());
            transaction.commit();
            if (printOut) {
                for (Staff staff :
                        staffResultList) {
//                    System.out.println(staffClass.getStaff_id() + ". " + staffClass.getPerson());
                    System.out.println(staff.getId() + ". " + staff.getPerson());

                }
            }
            return  staffResultList;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    // Behövs inte uppdatera staff tror jag

    public boolean update(Staff staff){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(staff);
            transaction.commit();
            return true;
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    //Deletea staff utifrån
    public boolean deleteStaff(int staff_id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();


            Staff staffToDelete = entityManager.find(Staff.class, staff_id);

            if (staffToDelete != null) {
                entityManager.remove(staffToDelete);
                transaction.commit();
                return true;
            }

            transaction.rollback();
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }
}
