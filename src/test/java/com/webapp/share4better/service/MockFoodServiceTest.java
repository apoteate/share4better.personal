package com.webapp.share4better.service;

import com.webapp.share4better.model.MockFood;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MockFoodServiceTest {
    @Autowired
    private IMockFoodService service;

    MockFood createMock(int id, int cId, int rId, String name, String type, String quantity, String quality) {
        MockFood dummy = new MockFood();
        dummy.setId(id);
        dummy.setContributorID(cId);
        dummy.setReceiverID(rId);
        dummy.setName(name);
        dummy.setType(type);
        dummy.setQuality(quality);
        dummy.setQuantity(quantity);
        return dummy;
    }

    void assertMockFood(MockFood expected, MockFood actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getContributorID(), actual.getContributorID());
        assertEquals(expected.getReceiverID(), actual.getReceiverID());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getQuality(), actual.getQuality());
        assertEquals(expected.getQuantity(), actual.getQuantity());
    }

    void assertData(Iterator<MockFood> expected, Iterator<MockFood> actual) {
        while (expected.hasNext() && actual.hasNext()) {
            assertMockFood(expected.next(), actual.next());
        }
    }

    @Test
    void getAllContributedFood() {
        List<MockFood> expectedMultipleSameReceiver = new ArrayList<>();
        expectedMultipleSameReceiver.add(createMock(1, 0, 1, "f1", "t1", "n1", "q1"));
        expectedMultipleSameReceiver.add(createMock(2, 0, 1, "f2", "t2", "n2", "q2"));
        assertEquals(expectedMultipleSameReceiver.size(), ((List<MockFood>) service.getAllContributedFood(0)).size());
        assertData(expectedMultipleSameReceiver.iterator(), service.getAllContributedFood(0).iterator());

        List<MockFood> expectedMultipleDifferentReceiver = new ArrayList<>();
        expectedMultipleDifferentReceiver.add(createMock(3, 1, 0, "f3", "t3", "n3", "q3"));
        expectedMultipleDifferentReceiver.add(createMock(4, 1, 2, "f4", "t4", "n4", "q4"));
        assertEquals(expectedMultipleDifferentReceiver.size(), ((List<MockFood>) service.getAllContributedFood(1)).size());
        assertData(expectedMultipleDifferentReceiver.iterator(), service.getAllContributedFood(1).iterator());

        List<MockFood> expectedSingleReceiver = new ArrayList<>();
        expectedSingleReceiver.add(createMock(5, 2, 0, "f5", "t5", "n5", "q5"));
        assertEquals(expectedSingleReceiver.size(), ((List<MockFood>) service.getAllContributedFood(2)).size());
        assertData(expectedSingleReceiver.iterator(), service.getAllContributedFood(2).iterator());

        // Test for contributor that doesn't exist in MockFood
        assertEquals(0, ((List<MockFood>) service.getAllContributedFood(999)).size());
    }

    @Test
    void getAllReceivedFood() {
        List<MockFood> expectedMultipleSameContributor = new ArrayList<>();
        expectedMultipleSameContributor.add(createMock(1, 0, 1, "f1", "t1", "n1", "q1"));
        expectedMultipleSameContributor.add(createMock(2, 0, 1, "f2", "t2", "n2", "q2"));
        assertEquals(expectedMultipleSameContributor.size(), ((List<MockFood>) service.getAllReceivedFood(1)).size());
        assertData(expectedMultipleSameContributor.iterator(), service.getAllReceivedFood(1).iterator());

        List<MockFood> expectedMultipleDifferentContributor = new ArrayList<>();
        expectedMultipleDifferentContributor.add(createMock(3, 1, 0, "f3", "t3", "n3", "q3"));
        expectedMultipleDifferentContributor.add(createMock(5, 2, 0, "f5", "t5", "n5", "q5"));
        assertEquals(expectedMultipleDifferentContributor.size(), ((List<MockFood>) service.getAllReceivedFood(0)).size());
        assertData(expectedMultipleDifferentContributor.iterator(), service.getAllReceivedFood(0).iterator());

        List<MockFood> expectedSingleContributor = new ArrayList<>();
        expectedSingleContributor.add(createMock(4, 1, 2, "f4", "t4", "n4", "q4"));
        assertEquals(expectedSingleContributor.size(), ((List<MockFood>) service.getAllReceivedFood(2)).size());
        assertData(expectedSingleContributor.iterator(), service.getAllReceivedFood(2).iterator());

        // Test for receiver that doesn't exist in MockFood
        assertEquals(0, ((List<MockFood>) service.getAllReceivedFood(999)).size());
    }
}