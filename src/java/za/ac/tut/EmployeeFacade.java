/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author User
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> implements EmployeeFacadeLocal {

    @PersistenceContext(unitName = "CT5EJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }

    @Override
    public List<Employee> displayEmplFromdept(String department) {
        
        Query q = em.createQuery("SELECT c FROM Employee c WHERE c.department = :departmentName");
        q.setParameter("departmentName", department);
        List<Employee> list = q.getResultList();
        return list;
    }

    @Override
    public Employee highestPaid(String department) {
        
        Query p = em.createQuery("SELECT c FROM Employee c WHERE c.salary = (SELECT MIN(c.salary) FROM Employee c WHERE c.department = :departmentName) AND c.department = :departmentName");
        p.setParameter("departmentName", department);
        Employee hi = (Employee)p.getSingleResult();
        return hi;
    }

}
