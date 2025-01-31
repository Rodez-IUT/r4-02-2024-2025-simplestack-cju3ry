package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test behaviour of a SimpleStack")
class SimpleStackTest {

    private SimpleStack stack;

    private Item item;

    @BeforeEach
    public void setUp() {
        stack = new SimpleStack();
        item = new SimpleItem();
    }

    @Test
    @DisplayName("Test the state of a newly created stack")
    public void testCreateEmptyStack() { // Test case

        // When a freshly stack is created (beforeEach)
        // Then… (oracle)
        assertTrue(stack.isEmpty(), "A new stack must be empty");
        assertEquals( 0, stack.getSize(), "A new stack has no element");
    }

    @Test
    @DisplayName("Test the push of items")
    public void testPush() throws EmptyStackException {

        // Given an empty stack and an item (beforeEach)
        // When the item is pushed in the stack
        stack.push(item);

        // Then…
        assertFalse(stack.isEmpty(), "The stack must not be empty");
        assertEquals(1, stack.getSize(),"The stack must constain 1 item");
        assertSame( item, stack.peek(),"The pushed item must be is on top of the stack");

        // Given a new item to add
        Item item2 = new SimpleItem();

        // When we add the new item
        stack.push(item2);

        // then...
        assertFalse(stack.isEmpty(), "The stack must be not empty");
        assertEquals(2, stack.getSize(),"The stack must constain 2 items");
        assertSame( item2, stack.peek(),"The pushed item must be on top of the stack");
    }

    @Test
    @DisplayName("Test limit when trying to pop an empty stack")
    public void testPopOnEmptyStack()  {
        // Given an empty stack (beforeEach)
        // When we "pop" the stack, should throws an EmptyStackException.
        //assertThrows(EmptyStackException.class, ()->stack.pop(), "EmptyStackException not thrown");
        assertThrows(EmptyStackException.class, stack::pop, "EmptyStackException not thrown");
    }

    @Test
    @DisplayName("Test pop in a normal utilisation")
    public void testPopOnNonEmptyStack() throws EmptyStackException {
        // Given a stack (beforeEach)
        stack.push(item);

        // When we "pop" the stack, should return the item
        Item returnItem = stack.pop();

        //Then the stack must be empty
        assertTrue(stack.isEmpty(), "The stack is empty");

        //And the po return the item
        assertEquals(returnItem, item, "Both items must be equals");
    }

    @Test
    @DisplayName("Test peek in a normal utilisation")
    public void testPeek() throws EmptyStackException {
        // Given a stack (beforeEach)
        stack.push(item);

        // When we "peek" the stack, should return the item at the top
        Item returnItem = stack.peek();

        //Then the return item must be the correct item
        assertEquals(returnItem, item, "Both items must be equals");
    }

    @Test
    @DisplayName("Test peek in a limit utilisation")
    public void testPeekOnEmptyStackException() {
        // Given a stack (beforeEach)
        // When we try to "peek" the stack, an exception is throws
        assertThrows(EmptyStackException.class, stack::peek, "EmptyStackException not thrown");
    }
}
