// Insertion Sort

#include <stdio.h>
int main()
{
    int n,i,j,k,temp;
    printf("Enter number of numbers to be sorted : ");
    scanf("%d",&n);
    int arr[n];
    for(i=0;i<n;i++)
    {
        printf("Enter %d : ",i+1);
        scanf("%d",&arr[i]);
    }
    for(i=1;i<n;i++)
    {
        for(j=i-1;j>=0;j--)
        {
            if (arr[i]>arr[j])
            {
                break;
            }
        }
        if (i-j>1)
        {
            for(k=i;k>j+1;k--)
            {
                temp=arr[k];
                arr[k]=arr[k-1];
                arr[k-1]=temp;
            }
        }
    }
    printf("\nSorted list is : ");
    for(i=0;i<n;i++)
    {
        printf("%d ",arr[i]);
    }
    return 0;
}