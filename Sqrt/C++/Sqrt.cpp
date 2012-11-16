#include <time.h>
#include <conio.h>
#include <math.h>
#include <stdio.h>
#include <windows.h>

float fast_sqrt0(float number){
  float x = number;
  float half_x = 0.5*x;
  long integer = *(long*)&x;
  integer = 0x5f3759df - (integer >> 1);
  x = *(float*)&integer;
  return x*number;
}

float fast_sqrt1(float number){
  float x = number;
  float half_x = 0.5*x;
  long integer = *(long*)&x;
  integer = 0x5f3759df - (integer >> 1);
  x = *(float*)&integer;
  x*=(1.5 - (half_x*x*x));
  return x*number;
}

float fast_sqrt2(float number){
  float x = number;
  float half_x = 0.5*x;
  long integer = *(long*)&x;
  integer = 0x5f3759df - (integer >> 1);
  x = *(float*)&integer;
  x*=(1.5 - (half_x*x*x));
  x*=(1.5 - (half_x*x*x));
  return x*number;
}

float fast_sqrt3(float number){
  float x = number;
  float half_x = 0.5*x;
  long integer = *(long*)&x;
  integer = 0x5f3759df - (integer >> 1);
  x = *(float*)&integer;
  x*=(1.5 - (half_x*x*x));
  x*=(1.5 - (half_x*x*x));
  x*=(1.5 - (half_x*x*x));
  return x*number;
}

float pow(float number){
  return pow(number,0.5);
}

void testar_desempenho(char* nome, int num_testes, int repeticoes, float (*foo)(float)) {
  double time_start, time_stop, total_time, sum_times;
  sum_times = 0;
  srand ( time(NULL) );
  printf("Teste - %s\n", nome);
  for (int i = 0; i < num_testes; ++i) {
    time_start = (double)clock()/CLOCKS_PER_SEC;
    for (int j = 0; j < repeticoes; ++j) {
      float temp = foo(1.312371232);
      float temp2 = foo(3.254536726);
      float temp3 = foo(15.42342343);
      float temp4 = foo(1359.234233);
      float temp5 = foo(13214124.23);
      float temp6 = foo(8989634345.0);
    }
    time_stop = (double)clock()/CLOCKS_PER_SEC;
    total_time = (time_stop - time_start)*1000;
    printf("%d/%d = %f\n", i+1, num_testes, total_time);
    sum_times += total_time;
  }
  printf("Media: %f\n\n", sum_times/num_testes);
}

void testar_desempenho2(char* nome, int num_testes, int repeticoes, double (*foo)(double)) {
  double time_start, time_stop, total_time, sum_times;
  sum_times = 0;
  srand ( time(NULL) );
  printf("Teste - %s\n", nome);
  for (int i = 0; i < num_testes; ++i) {
    time_start = (double)clock()/CLOCKS_PER_SEC;
    for (int j = 0; j < repeticoes; ++j) {
      float temp = foo(1.312371232);
      float temp2 = foo(3.254536726);
      float temp3 = foo(15.42342343);
      float temp4 = foo(1359.234233);
      float temp5 = foo(13214124.23);
      float temp6 = foo(8989634345.0);
    }
    time_stop = (double)clock()/CLOCKS_PER_SEC;
    total_time = (time_stop - time_start)*1000;
    printf("%d/%d = %f\n", i+1, num_testes, total_time);
    sum_times += total_time;
  }
  printf("Media: %f\n\n", sum_times/num_testes);
}

int main() {
  int num_testes = 5;
  int num_repeticoes = 10000000;
  testar_desempenho("Fast Sqrt 0", num_testes, num_repeticoes, &fast_sqrt0);
  testar_desempenho("Fast Sqrt 1", num_testes, num_repeticoes, &fast_sqrt1);
  testar_desempenho("Fast Sqrt 2", num_testes, num_repeticoes, &fast_sqrt2);
  testar_desempenho("Fast Sqrt 3", num_testes, num_repeticoes, &fast_sqrt3);
  testar_desempenho2("Sqrt", num_testes, num_repeticoes, &sqrt);
  testar_desempenho("Pow", num_testes, num_repeticoes, &pow);

  getch();
  return 0;
}
