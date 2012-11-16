#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <conio.h>
#include <windows.h>
#include <process.h>
#include <math.h>

int n = 100000;
int num_testes = 5;
long vetor[100000];
long resp_threadless[100000];
long resp_thread[100000];
long pos = 0;

HANDLE  hCountMutex = CreateSemaphore(NULL, 1, 1, NULL);

void buildvector() {
  srand ( time(NULL) );
  for (int i = 0; i < n; ++i) {
    vetor[i] = (rand() % n) + 1;
  }
}

DWORD WINAPI function_th( LPVOID lpParam ) {
  //printf("ID: %d\n",lpParam);
  while (true) {
    WaitForSingleObject(hCountMutex, 1);
    long j = pos;
    ++pos;
    ReleaseSemaphore(hCountMutex, 1, NULL);
     
    if (j >= n) {
      break;
    }
    
    int count = 0;
    double x = vetor[j];
    //for (int i = 0; i < n; ++i) {
    while (x > 1.0) {
      //0.97
      x = x*0.97;
      ++count;
    }
    //}
    //printf("ID: %d - %d - %d \n", lpParam, j, count);
    resp_thread[j] = count;
  }
}

void function() {
  for (long j = 0; j < n; ++j) {
    int count = 0;
    double x = vetor[j];
    while (x > 1.0) {
      x = x*0.97;
      ++count;
    }
    resp_threadless[j] = count;
  }
  
}

void threaded(int num_threads) {
  double time_start, time_stop, total_time, sum_times;
  sum_times = 0;
  printf("Threaded %d\n", num_threads);
  for (int i = 0; i < num_testes; ++i) {
    time_start = (double)clock()/CLOCKS_PER_SEC;
    
    pos = 0;
    HANDLE hThreadArray[num_threads];
    DWORD dwThreads[num_threads];
    for (int j = 0; j < num_threads; ++j) {
      hThreadArray[j] = CreateThread(NULL, 0, function_th, (void*)j, 0, &(dwThreads[j]));
    }
    WaitForMultipleObjects(num_threads, hThreadArray, TRUE, INFINITE);
    for(int j = 0; j < num_threads; ++j)
    {
      //CloseHandle(hThreadArray[j]);
    }
    
    time_stop = (double)clock()/CLOCKS_PER_SEC;
    total_time = (time_stop - time_start)*1000;
    printf("%d/%d = %f\n", i+1, num_testes, total_time);
    sum_times += total_time;
  }
  printf("Media: %f\n\n", sum_times/num_testes);
}

void threadless() {
  double time_start, time_stop, total_time, sum_times;
  sum_times = 0;
  printf("Threadless\n");
  for (int i = 0; i < num_testes; ++i) {
    time_start = (double)clock()/CLOCKS_PER_SEC;
    
    function();
    
    time_stop = (double)clock()/CLOCKS_PER_SEC;
    total_time = (time_stop - time_start)*1000;
    printf("%d/%d = %f\n", i+1, num_testes, total_time);
    sum_times += total_time;
  }
  printf("Media: %f\n\n", sum_times/num_testes);
}

int main() {
  buildvector();
  threadless();
  threaded(1);
  threaded(2);
  //threaded(3);
  threaded(4);
  //threaded(5);
  //threaded(6);
  threaded(8);
  threaded(16);
  getch();
}
