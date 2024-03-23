import java.math.BigInteger;

public class Functions 
{  
    public double findMean(int[] userInput) 
    {
        double sum = 0;
        for(int i = 0; i < userInput.length; i++)
        {
            sum = userInput[i] + sum;
        }
        double result = sum / userInput.length;
        return result;
    }      
    
    public int findMedian(int[] userInput) 
    {
        int result;
        if(userInput.length%2 != 0)//odd 
        {
            int medIndex = userInput.length/2; //rounds up (.5)
            result = userInput[medIndex];
        }
        else //even
        {
            int first = userInput.length/2;
            int second = userInput.length/2 + 1;
            result = (first+second)/2;
        }
        return result;        
    }
    
    public int findMode(int[] userInput) 
    {
        int count;
        int maxCount = 0;
        int maxNum = 0;
        int[] counts = new int[userInput.length];
        
        for(int i = 0; i < userInput.length; i++)
        {
            count = 0;
            for(int j = 0; j < userInput.length; j++)
            {
                if(userInput[i] == userInput[j])
                {
                    count++;
                }
            }
            if(count > maxCount)
            {
                maxCount = count;
                maxNum = userInput[i];
            }
        }
        return maxNum;
    }
    
    public void remove(int index, int[] input)
    {
        int k = 0;
        for (int i = index+1; i < input.length; i++)
        {
            input[i-1] = input[i];
            k++;
        }
        input[index+k] = 0;
    }
    
    public int[] union(int[] set1, int set2[]) //used for reference: https://stackoverflow.com/questions/51113134/union-or-intersection-of-java-sets
    {
        int[] temp = new int[set1.length+set2.length];
        for(int i = 0; i < set1.length; i++)
        {
            temp[i] = set1[i];
        }
        int k = 0;
        for(int i = set1.length; i < temp.length-1; i++)
        {
            temp[i] = set2[k];
            k++;
        }
            
        for (int i = 0; i < set1.length; i++) 
        {
            for(int j = 0; j < set2.length; j++)
            {
                if(temp[i] == (temp[j]) && i != j) 
                {
                    remove(i, temp);
                }
            }
        }
        return temp;
    }
    
    public int[] intersection(int[] set1, int[] set2) 
    {
        int k = 0;
        int[] temp = new int[set1.length];
        for(int i = 0; i < set1.length; i++)
        {
            for(int j = 0; j < set2.length; j++)
            {
                if(set1[i] == set2[j]) 
                {
                    temp[k] = set1[i];
                    k++;
                }
            }
        }
        return temp;
    }
    
    public int[] complement(int[] set, int[] sample)
    {
        int[] temp = new int[sample.length];
        for(int i = 0; i < set.length; i++)
        {
            temp[i] = sample[i];
        }
        for(int i = 0; i < set.length; i++)
        {
           for(int j = 0; j < set.length; j++)
           {
               if(temp[i] == set[j])
               {
                   remove(i, temp);
               }
           }  
        }
        return temp;
    }
    
    public void printArray(int[] input)
    {
        System.out.print("[");
        for(int i = 0; i < input.length; i++)
        {
            System.out.print(input[i]);
            System.out.print(",");
        }
        System.out.print("]");
    }
    
    public double findVariance(int[] userInput)
    {
        double firstMean = findMean(userInput);
        double sum = 0;
        for(int i=0; i < userInput.length; i++)
        {
            sum = sum + (Math.pow((userInput[i] - firstMean),2));
        }
        return sum /(userInput.length - 1);
    }
    
    public double findStandardDev(int[] userInput)
    {
        return Math.sqrt(findVariance(userInput));
    }
    
    public double conditionalProb(int[] A, int[] B)
    {
        double probIntersect = ((intersection(A,B).length)/(B.length+A.length));
        double probB = (B.length/(B.length+A.length));
        return probB;
    }
    
    public boolean isIndependent(int[] A, int[] B)
    {
        double probA = (A.length/(A.length+B.length));
        double probB = (B.length/(B.length+A.length));
        
        if(((conditionalProb(A,B)) == probA) && ((conditionalProb(B,A)) == probB) && ((intersection(A,B).length) == (probA*probB)))
        {
            return true;      
        }
        else
        {
            return false;
        }
    }
    
    public BigInteger bigFactorial(int num)
    {
        BigInteger Temp = BigInteger.valueOf(1); //holds total factorial. initalized at 1 to handle 0!
        BigInteger I = BigInteger.valueOf(1); //holds i value. starts at first loop val 1
        for(int i = 1; i <= num; i++)
        {
            I = I.valueOf(i); //this loop's factor
            Temp = Temp.multiply(I); //previous temp * i
        }
        return Temp;
    }
    
    public long longFactorial(int num) //bigFactorial() modified to handle longs. used for rest of methods
    {
        Long temp = (long)1; //holds total factorial. initalized at 1 to handle 0!
        Long iVal = (long)1; //holds i value. starts at first loop val 1
        for(int i = 1; i <= num; i++)
        {
            iVal = (long)i; //casting i to long, this loop's factor
            temp = temp*i; //previous temp * i
        }
        return temp;
    }
    
    public long permutation(int n, int r) 
    {
        return longFactorial(n)/longFactorial(n-r); 
    }
    
    public long combination(int n, int r) 
    {
        return longFactorial(n)/(longFactorial(r)*longFactorial(n-r));
    }
    
    public double binomialDist(int n, int y, double p, double q) //does q have to be param? 
    {
        return combination(n,y)*Math.pow(p,y)*Math.pow(q,(n-y));
    }
    
    public double binomialExpected(int n, double p) 
    {
        return n*p;
    }
    
    public double binomialVariance(int n, double p, double q) 
    {
        return n*p*q;
    }
    
    public double geometricDist(int y, double p, double q) 
    {
        return Math.pow(q, y-1)*p;
    }
    
    public double geometricExpected(double p) 
    {
        return 1/p;
    }
    
    public double geometricVariance(double p) 
    {
        return (1-p)/Math.pow(p,2);
    }
    
    public double hypergeometricDist(int N, int n, int r, int y)
    {
        long c1 = combination(r,y); //seperated for readability
        long c2 = combination(N-r, n-y);
        long c3 = combination(N, n);
        return (double) (c1*c2)/c3; 
    }
    
    public double hypergeometricExpected(int N, int n, int r) 
    {
        return (n*r)/N;
    }
    
    public double hypergeometricVariance(int N, int n, int r) 
    {
        long c1 = combination(r,N); //seperated for readability
        long c2 = combination(N-r, N);
        long c3 = combination(N-n, N-1);
        return (double) n*c1*c2*c3;
    }
    
    public double negativeBinomialDist(int r, int y, double p, double q) 
    {
        return (double) combination(y-1, r-1)*Math.pow(p,r)*Math.pow(q, y-r);
    }
    
    public double negativeExpected(int r, double p) 
    {
        return r/p;
    }
    
    public double negativeVariance(int r, double p) 
    {
        return (r*(1-p))/(Math.pow(p,2));
    }
}