package interview.leetcode;

/**
 * There are two sorted arrays A and B of size m and n respectively. Find the
 * median of the two sorted arrays. The overall run time complexity should be
 * O(log (m+n)).
 * 
 * @author yazhoucao
 * 
 */
public class Median_of_Two_Sorted_Arrays {

	public static void main(String[] args) {
		Median_of_Two_Sorted_Arrays o = new Median_of_Two_Sorted_Arrays();
		int[] A0 = {};
		int[] B0 = {2,3};
		System.out.println(o.findMedianSortedArrays(A0, B0));	//6
		
		
		int[] A1 = {4,5,6,8,9};
		int[] B1 = {};
		System.out.println(o.findMedianSortedArrays(A1, B1));	//6
	}
	

	/**
	 * Converted to a find Kth element problem, the k = m+n/2
	 */
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		if ((m+n) % 2 == 1)
			return findKth(A, 0, m, B, 0, n, (m+n+1)/2);
		else
			return (findKth(A, 0, m, B, 0, n, (m+n)/2) + findKth(A, 0, m, B, 0, n, (m+n)/2 + 1))/2;
	}

	public double findKth(int A[], int startA, int endA, int B[], int startB,
			int endB, int k) {
		int m = endA - startA;
		int n = endB - startB;

		if (n <= 0)
			return A[startA + k - 1];
		if (m <= 0)
			return B[startB + k - 1];
		if (k == 1)
			return A[startA] < B[startB] ? A[startA] : B[startB];

		int midA = (startA + endA) / 2;
		int midB = (startB + endB) / 2;

		if (A[midA] > B[midB]) {
			if (m /2 + n / 2 + 1 >= k) // drop A second half
				return findKth(A, startA, midA, B, startB, endB, k);
			else
				// drop B first half
				return findKth(A, startA, endA, B, midB + 1, endB, k - n / 2 - 1);
		} else {
			if (m / 2 + n/ 2 + 1 >= k) // drop B second half
				return findKth(A, startA, endA, B, startB, midB, k);
			else
				// drop A first half
				return findKth(A, midA + 1, endA, B, startB, endB, k - m / 2 - 1);
		}
	}
	
	
	/**
	 * Version 2
	 * 
	 */
    public double findMedianSortedArrays2(int A[], int B[]) {
        int lengthA = A.length;
        int lengthB = B.length;
        if ((lengthA + lengthB) % 2 == 0) {
            double r1 = (double) findMedianSortedArrays(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB) / 2);
            double r2 = (double) findMedianSortedArrays(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB) / 2 + 1);
            return (r1 + r2) / 2;
        } else
            return findMedianSortedArrays(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB + 1) / 2);
    }
    
    public int findMedianSortedArrays(int A[], int startA, int endA, int B[], int startB, int endB, int k) {
        int m = endA - startA;
        int n = endB - startB;
 
        if (m <= 0)
            return B[startB + k - 1];
        if (n <= 0)
            return A[startA + k - 1];
        if (k == 1)
            return A[startA] < B[startB] ? A[startA] : B[startB];
 
        int midA = (startA + endA) / 2;
        int midB = (startB + endB) / 2;
 
        if (A[midA] <= B[midB]) {
            if (m / 2 + n / 2 + 1 >= k)
                return findMedianSortedArrays(A, startA, endA, B, startB, midB, k);
            else
                return findMedianSortedArrays(A, midA + 1, endA, B, startB, endB, k - m / 2 - 1);
        } else {
            if (m / 2 + n / 2 + 1 >= k)
                return findMedianSortedArrays(A, startA, midA, B, startB, endB, k);
            else
                return findMedianSortedArrays(A, startA, endA, B, midB + 1, endB, k - n / 2 - 1);
 
        }
    }
}
