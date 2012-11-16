#include <time.h>
#include <conio.h>
#include <stdio.h>
#include <windows.h>

int base(int a, int b){
  return 0;
}

int metodo1(int a, int b){
  return (a+b)/2;
}

int metodo2(int a, int b){
  return (a + b) >> 1;
}

int metodo3(int a, int b){
  int mean = (a>>1) + (b>>1);
  if ((a^b) >= 0 && (a&b&1)){	
    if (a>=0)
      return mean + 1;
    else
      return mean - 1;
  }
  return mean;
}

int metodo4(int a, int b){
  return (a&b) + ((a^b) >> 1);
}

int metodo5(int a, int b){
  int c = a&b;
  int d = a^b;
  d = d >> 1;
  return c + d ;
}

void testar_desempenho(char* nome, int num_testes, int repeticoes, int (*foo)(int,int)) {
  double time_start, time_stop, total_time, sum_times;
  sum_times = 0;
  srand ( time(NULL) );
  printf("Teste - %s\n", nome);
  for (int i = 0; i < num_testes; ++i) {
    time_start = (double)clock()/CLOCKS_PER_SEC;
    for (int j = 0; j < repeticoes; ++j) {
      int temp = foo(rand(),rand());
    }
    time_stop = (double)clock()/CLOCKS_PER_SEC;
    total_time = time_stop - time_start;
    printf("%d/%d = %f\n", i+1, num_testes, total_time);
    sum_times += total_time;
  }
  printf("Media: %f\n\n", sum_times/num_testes);
}

void imprime_se_erro(int expected, int result) {
  if (expected != result) {
    printf("Expected: %d -  Result: %d\n", expected, result);
  }
}

void testar(char* nome, int (*foo)(int,int)) {
  printf("Testando: %s\n", nome);
  imprime_se_erro(2147483647,foo(2147483647,2147483647));
  imprime_se_erro(15,foo(20,10));
  imprime_se_erro(16,foo(11,21));
  imprime_se_erro(10,foo(10,11));
  imprime_se_erro(-5,foo(-20,10));
  imprime_se_erro(50,foo(0,100));
  imprime_se_erro(0,foo(0,0));
}

int main() {
  printf("Testes de Unidade:\n");
  testar("Metodo 1 - (a+b)/2", metodo1);
  testar("Metodo 2 - (a + b) >> 1", metodo2);
  testar("Metodo 3 - (a >> 1) + (b >> 1) + something", metodo3);
  testar("Metodo 4 - (a&b) + ((a^b) >> 1)", metodo4);
  testar("Metodo 5 - (a&b) + ((a^b) >> 1 - v2)", metodo5);

  int num_repeticoes = 100000000;
  int num_testes = 5;
  
  printf("\nTestes de Desempenho:\n");
  testar_desempenho("Base", num_testes, num_repeticoes, &base);
  testar_desempenho("Metodo 1 - (a+b)/2", num_testes, num_repeticoes, &metodo1);
  testar_desempenho("Metodo 2 - (a + b) >> 1", num_testes, num_repeticoes, &metodo2);
  testar_desempenho("Metodo 3 - (a >> 1) + (b >> 1) + something", num_testes, num_repeticoes, &metodo3);
  testar_desempenho("Metodo 4 - (a&b) + ((a^b) >> 1)", num_testes, num_repeticoes, &metodo4);
  testar_desempenho("Metodo 5 - (a&b) + ((a^b) >> 1)", num_testes, num_repeticoes, &metodo5);

  getch();
  return 0;
}
