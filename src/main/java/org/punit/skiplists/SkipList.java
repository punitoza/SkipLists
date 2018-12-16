package org.punit.skiplists;

import java.util.Random;

public class SkipList {
	SkipListNode topLeft = new SkipListNode(Integer.MIN_VALUE);
	public static final float PROBABILITY = 0.5f;
	public SkipList() {
		SkipListNode topRight = new SkipListNode(Integer.MAX_VALUE);
		topLeft.right = topRight;
	}
	public SkipListNode search(int target) {
		SkipListNode result = searchClosestSmallerOrEqual(target);
		if (result.val == target) {
			return result;
		} else {
			return null;
		}
	}
	public SkipListNode searchClosestSmallerOrEqual(int target) {
		SkipListNode head = topLeft;
		SkipListNode result = topLeft;
		while (head != null) {
			while (target >= head.right.val) {
				head = head.right;
			}
			if (head.val == target) {
				return head;
			}
			result = head;
			head = head.bottom;
		}
		return result;
	}
	public void insert(int target) {
		if (target == Integer.MIN_VALUE || target == Integer.MAX_VALUE) {
			throw new RuntimeException("Cannot insert because the value is not a valid value");
		}
		SkipListNode left = searchClosestSmallerOrEqual(target);
		Random rand = new Random();
		SkipListNode bottom = null;
		while (left.bottom != null) {
			left = left.bottom;
		}
		while (true) {
			SkipListNode current = new SkipListNode(target);
			SkipListNode temp = left.right;
			left.right = current;
			current.left = left;
			current.right = temp;
			temp.left = current;
			if (bottom != null) {
				current.bottom = bottom;
				current.bottom.top = current;
			}
			if (rand.nextFloat() < PROBABILITY) {
				break;
			}
			bottom = current;
			while (current.top == null && current.left != null) {
				current = current.left;
			}
			if (current.left == null) { // Reached negative infinity node
				if (current.top != null && current.top.right.val == Integer.MAX_VALUE) {
					break;
				}
				if (current.top == null) {
					current.top = new SkipListNode(Integer.MIN_VALUE);
					current.top.right = new SkipListNode(Integer.MAX_VALUE);
					current.top.right.left = current.top;
					current.top.bottom = current;
				}
			}
			left = current.top;
		}
	}
	public void delete(int target) {
		if (target == Integer.MIN_VALUE || target == Integer.MAX_VALUE) {
			throw new RuntimeException("Cannot delete because value is not present in the list");
		}
		SkipListNode delNode = search(target);
		if (delNode == null) {
			throw new RuntimeException("Cannot delete because value is not present in the list");
		}
		SkipListNode left = delNode.left;
		while (delNode != null) {
			delNode.top = null;
			delNode.left.right = delNode.right;
			delNode.right.left = delNode.left;
			delNode = delNode.bottom;
		}
		if (left.left == null && left.right.right == null && topLeft != left) {
			topLeft = left;
			left.top.right.left = null;
			left.top.right = null;
			left.top = null;
		}
	}
}
