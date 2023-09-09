#include<stdio.h>
int main()
{
    int n;
    printf("Enter the size of matrix nxn");
    scanf("%d",&n);
    int a[n][n], b[n][n];
    int i,j;
    printf("Enter the elements in the first array");
    for( i=0;i<n;i++)
    {
        for( j=0;j<n;j++)
        {
            printf("Enter the element %d,%d ",i,j);
            scanf("%d",&a[i][j]);
        }
    }
printf("Enter the elements for the 2nd array");
    for( i=0;i<n;i++)
    {
        for(j=0;j<n;j++)
        {
            printf("Enter the element %d,%d ",i,j);
            scanf("%d",&b[i][j]);
        }
    }
int c[n][n];
int sum=0;

    for( i=0;i<n;i++)
    {
        
        for(j=0;j<n;j++)
        {
            sum=0;
            for(int k=0;k<n;k++)
            {
            sum=sum+(a[i][k]*b[k][j]);
            }
            c[i][j]=sum;
        }
        
    }

for(i=0;i<n;i++)
{
    for(j=0;j<n;j++)
    {
        printf("%d ",c[i][j]);
    }
    printf("\n");
}
return 0;
}

