import java.util.Arrays;
import java.util.Random;

class Merge
{
    int contComp = 0, contMovim = 0;
    public void vetorCrescente(int[] vetor, int n)
    {
        vetor = new int[n];
        preencheVetor(vetor, 1, vetor.length);
        mergeSort(vetor, 0, vetor.length - 1);
        System.out.print("\nNúmero de comparações: " + contComp);
        System.out.print("\nNúmero de movimentações: " + contMovim);
        System.out.print("\n\n*------------------------*");
        contComp = 0;
        contMovim = 0;
    }

    public void vetorDecrescente(int[] vetor, int n)
    {
        vetor = new int[n];
        preencheVetor(vetor, vetor.length, 1);
        mergeSort(vetor, 0, vetor.length - 1);
        System.out.print("\nNúmero de comparações: " + contComp);
        System.out.print("\nNúmero de movimentações: " + contMovim);
        System.out.print("\n\n*------------------------*");
        contComp = 0;
        contMovim = 0;
    }
    
    public void vetorRandom(int[] vetor, int n)
    {
        vetor = new int[n];
        preencheVetor(vetor, vetor.length, vetor.length);
        if(n == 10)
            System.out.println("\n" + Arrays.toString(vetor));
        
        mergeSort(vetor, 0, vetor.length - 1);
        System.out.print("\nNúmero de comparações: " + contComp);
        System.out.print("\nNúmero de movimentações: " + contMovim);
        if(n == 10)
            System.out.println("\n\n" + Arrays.toString(vetor));
        System.out.print("\n\n*------------------------*");
        contComp = 0;
        contMovim = 0;
    }
    
    public void preencheVetor(int[] vet, int ini, int fim)
    {
        int aux;
        if(ini < fim)
        {
            aux = 1;
            for(int i = 0; i < vet.length; i++)
            {
                vet[i] = aux;
                aux++;
            }
        }
        else if(ini > fim)
        {
            aux = vet.length;
            for(int i = 0; i < vet.length; i++)
            {
                vet[i] = aux;
                aux--;
            }
        }
        else
        {
            Random number = new Random();
            int find = 0, c;
            for(int i = 0; i < vet.length; i++)
            {
                find = number.nextInt(vet.length) + 1;
                if ( i == 0 ) 
                {
                    vet[i] = find;
                } 
                else 
                {
                    c = 0;
                    while (c < i)
                    {
                        if (vet[c] == find)
                        {
                            find = number.nextInt(vet.length) + 1;
                            c = 0;
                        } 
                        else 
                        {                   
                            c++;
                        }
                    }
                    vet[i] = find;         
                }    
            }
        }
    }

    public void mergeSort(int A[], int inicio, int fim)
    {
        if(inicio < fim)
        {
            int meio = (inicio + fim) / 2; //calcula o meio
            mergeSort(A, inicio, meio); //ordena do subvetor esquerdo
            mergeSort(A, meio + 1, fim); //ordena do subvetor direito
            merge(A, inicio, meio, fim); //funde os subvetores esquerdo e direito
        }
    }    

    private void merge(int A[], int inicio, int meio, int fim)
    {
        int tamEsq = meio - inicio + 1; //tamanho do subvetor esquerdo
        int tamDir = fim - meio; //tamanho do subvetor direito
        int esq[] = new int[tamEsq]; //subvetor auxiliar esquerdo
        int dir[] = new int[tamDir]; //subvetor auxiliar direito
        int idxEsq = 0; //índice do subvetor auxiliar esquerdo
        int idxDir = 0; //índice do subvetor auxiliar direito
        //Copia os elementos do subvetor esquerdo para o vetor auxiliar
        for(int i = 0; i < tamEsq; i++)
        {
            esq[i] = A[inicio + i];
        }
        //Copia os elementos do subvetor direito para o vetor auxiliar
        for(int j = 0; j < tamDir; j++)
        {
            dir[j] = A[meio + 1 + j];
        }
        
        //intercala os subvetores
        for(int k = inicio; k <= fim; k++)
        {
            if(idxEsq < tamEsq)
            {
                contComp++;
                if(idxDir < tamDir)
                {
                    contComp++;
                    if(esq[idxEsq] < dir[idxDir])
                    {
                        contComp++;
                        A[k] = esq[idxEsq++];
                        contMovim++;
                    }
                    else
                    {
                        A[k] = dir[idxDir++];
                        contMovim++;
                    }
                }
                else
                {
                    A[k] = esq[idxEsq++];
                    contMovim++;
                }
            }
            else
            {
                A[k] = dir[idxDir++];
                contMovim++;
            }
        }   
    }
    
    
}
