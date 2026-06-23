package com.solvd.smarthome.service;

import com.solvd.smarthome.domain.Owner;
import com.solvd.smarthome.service.impl.OwnerServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.util.List;

public class OwnerServiceTest {

    private com.solvd.smarthome.service.OwnerService ownerService;
    private Owner testOwner;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("=== Starting Owner Test Suite ===");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Initializing OwnerServiceTest class");
        ownerService = new OwnerServiceImpl();
    }

    @BeforeMethod
    public void beforeMethod() {
        testOwner = new Owner();
        testOwner.setFirstName("Test");
        testOwner.setLastName("User");
        testOwner.setEmail("test" + System.currentTimeMillis() + "@example.com");
        testOwner.setPhone("+1234567890");
        testOwner.setBirthDate(LocalDate.of(1990, 1, 1));
    }

    @Test
    public void testCreateOwner() {
        ownerService.create(testOwner);
        Assert.assertNotNull(testOwner.getId(), "Owner ID should not be null after creation");
    }

    @Test
    public void testFindOwnerById() {
        ownerService.create(testOwner);
        Owner found = ownerService.findById(testOwner.getId());
        Assert.assertEquals(found.getFirstName(), testOwner.getFirstName(), "First name should match");
    }

    @Test
    public void testFindAllOwners() {
        ownerService.create(testOwner);
        List<Owner> owners = ownerService.findAll();
        Assert.assertTrue(owners.size() > 0, "Owner list should not be empty");
    }

    @Test
    public void testUpdateOwner() {
        ownerService.create(testOwner);
        testOwner.setFirstName("Updated");
        ownerService.update(testOwner);
        Owner updated = ownerService.findById(testOwner.getId());
        Assert.assertEquals(updated.getFirstName(), "Updated", "First name should be updated");
    }

    @Test
    public void testDeleteOwner() {
        ownerService.create(testOwner);
        Long id = testOwner.getId();
        ownerService.delete(id);
        Owner deleted = ownerService.findById(id);
        Assert.assertNull(deleted, "Owner should be null after deletion");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Test method finished");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("OwnerServiceTest class finished");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("=== Owner Test Suite Finished ===");
    }
}
