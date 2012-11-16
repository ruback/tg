#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <conio.h>
#include <windows.h>
#include <process.h>
#include <math.h>
#include <iostream>

long n = 10000;
long num_testes = 1;
long vetor[100000];
long resp_threadless[100000];
long resp_thread[100000];
long pos = 0;

using namespace std;

LARGE_INTEGER timerFreq_;
LARGE_INTEGER counterAtStart_;

//HANDLE  hCountMutex = CreateSemaphore(NULL, 1, 1, NULL);

void startTime()
{
  QueryPerformanceFrequency(&timerFreq_);
  QueryPerformanceCounter(&counterAtStart_);
  //cout<<"timerFreq_ = "<<timerFreq_.QuadPart<<endl;
  //cout<<"counterAtStart_ = "<<counterAtStart_.QuadPart<<endl;
  TIMECAPS ptc;
  UINT cbtc = 8;
  /*MMRESULT result = timeGetDevCaps(&ptc, cbtc);
  if (result == TIMERR_NOERROR)
  {
    cout<<"Minimum resolution = "<<ptc.wPeriodMin<<endl;
    cout<<"Maximum resolution = "<<ptc.wPeriodMax<<endl;
  }
  else
  {
    cout<<"result = TIMER ERROR"<<endl;
  }*/
}

double calculateElapsedTime()
{
  if (timerFreq_.QuadPart == 0)
  {
    return -1;
  }
  else
  {
    LARGE_INTEGER c;
    QueryPerformanceCounter(&c);
    return static_cast<double>( (c.QuadPart - counterAtStart_.QuadPart) * 1000 / timerFreq_.QuadPart );
  }
}

void buildvector() {
  srand ( time(NULL) );
  for (long i = 0; i < n; ++i) {
    vetor[i] = (rand() % n) + 1;
  }
}

DWORD WINAPI function_th( LPVOID lpParam ) {
  for (int k = 0; k < n; ++k){
  for (long j = 0; j < n; ++j) {
    long count = 0;
    double x = vetor[j];
    while (x > 1.0) {
      x = x/2;
      ++count;
    }
    resp_threadless[j] = count;
  }
  }
  for (double p = 15; p > 1; p = sqrt(p))
    printf("S: %.16f\n", p);
}

void function() {
  for (int k = 0; k < n; ++k){
  for (long j = 0; j < n; ++j) {
    long count = 0;
    double x = vetor[j];
    while (x > 1.0) {
      x = x/2;
      ++count;
    }
    resp_threadless[j] = count;
  }
  }
  for (double p = 15; p > 1; p = sqrt(p))
    printf("S: %.16f\n", p);
}

void threaded(long num_threads) {
  double time_start, time_stop, total_time, sum_times;
  sum_times = 0;
  printf("Threaded %d\n", num_threads);
  for (long i = 0; i < num_testes; ++i) {
    startTime();
    time_start = calculateElapsedTime();    
    
    pos = 0;
    HANDLE hThreadArray[num_threads];
    DWORD dwThreads[num_threads];
    for (long j = 0; j < num_threads; ++j) {
      hThreadArray[j] = CreateThread(NULL, 0, function_th, (void*)j, 0, &(dwThreads[j]));
    }
    WaitForMultipleObjects(num_threads, hThreadArray, TRUE, INFINITE);
    for(long j = 0; j < num_threads; ++j)
    {
      CloseHandle(hThreadArray[j]);
    }
    
    time_stop = calculateElapsedTime();    
    total_time = (time_stop - time_start);
    printf("%d/%d = %f\n", i+1, num_testes, total_time);
    sum_times += total_time;
  }
  printf("Media: %f\n\n", sum_times/num_testes);
}

void threadless() {
  double time_start, time_stop, total_time, sum_times;
  sum_times = 0;
  printf("Threadless\n");
  for (long i = 0; i < num_testes; ++i) {
    startTime();
    time_start = calculateElapsedTime();
    
    function();
    
    time_stop = calculateElapsedTime();
    total_time = (time_stop - time_start);
    printf("%d/%d = %f\n", i+1, num_testes, total_time);
    sum_times += total_time;
  }
  printf("Media: %f\n\n", sum_times/num_testes);
}

int main() {
  buildvector();
  threadless();
  threaded(1);  
  //threaded(2);
  //threaded(4);
  //threaded(8);
  //threaded(16);
  getch();
}
