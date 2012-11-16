#include <time.h>
#include <conio.h>
#include <stdio.h>
#include <windows.h>

class MyClass {
  
  public:
    bool a; 
    int i;
    MyClass(bool a, int i);
    char* func1();
    void func2();
};

MyClass::MyClass(bool a, int i) {
  this->a = a;
  this->i = i;
}

char* MyClass::func1() {
  return "Hello!";
}

void MyClass::func2() {
  (this->i)++;
}


int repetetions = 1000000000;
bool global_a = true;
MyClass global_obj(true,12345);

void variable_inside_for() {
  int aux = 0;
	for (int k = 0; k < repetetions; ++k) {
		bool a = true;
		if (a) {
			aux += 1;
    }
  }
}

void variable_outside_for() {
  int aux = 0;
  bool a = true;
	for (int k = 0; k < repetetions; ++k) {	
    a = true;
		if (a) {
			aux += 1;
    }
  }
}

void variable_outside_function(bool a) {
  int aux = 0;
	for (int k = 0; k < repetetions; ++k) {	
    a = true;
		if (a) {
			aux += 1;
    }
  }
}

void variable_global() {
  int aux = 0;
	for (int k = 0; k < repetetions; ++k) {	
    global_a = true;
		if (global_a) {
			aux += 1;
    }
  }
}

void object_inside_for() {
  int aux = 0;
	for (int k = 0; k < repetetions; ++k) {
		MyClass obj(true,12345);
		if (obj.a) {
			aux += 1;
    }
  }
}

void object_outside_for() {
  int aux = 0;
  MyClass obj(true,12345);
	for (int k = 0; k < repetetions; ++k) {	
    obj = MyClass(true,12345);
		if (obj.a) {
			aux += 1;
    }
  }
}

void object_outside_function(MyClass obj) {
  int aux = 0;
	for (int k = 0; k < repetetions; ++k) {	
    obj = MyClass(true,12345);
		if (obj.a) {
			aux += 1;
    }
  }
}

void object_global() {
  int aux = 0;
	for (int k = 0; k < repetetions; ++k) {	
    global_obj = MyClass(true,12345);
		if (global_obj.a) {
			aux += 1;
    }
  }
}

void testar_desempenho(char* nome, int num_testes, void (*foo)()) {
  double time_start, time_stop, total_time, sum_times;
  sum_times = 0;
  srand ( time(NULL) );
  printf("Teste - %s\n", nome);
  for (int i = 0; i < num_testes; ++i) {
    time_start = (double)clock()/CLOCKS_PER_SEC;
    foo();
    time_stop = (double)clock()/CLOCKS_PER_SEC;
    total_time = 1000*(time_stop - time_start);
    printf("%d/%d = %f\n", i+1, num_testes, total_time);
    sum_times += total_time;
  }
  printf("Media: %f\n\n", sum_times/num_testes);
}

void testar_desempenho_variable(char* nome, int num_testes, void (*foo)(bool)) {
  double time_start, time_stop, total_time, sum_times;
  sum_times = 0;
  srand ( time(NULL) );
  printf("Teste - %s\n", nome);
  for (int i = 0; i < num_testes; ++i) {
    time_start = (double)clock()/CLOCKS_PER_SEC;
    bool a = true;
    foo(a);
    time_stop = (double)clock()/CLOCKS_PER_SEC;
    total_time = 1000*(time_stop - time_start);
    printf("%d/%d = %f\n", i+1, num_testes, total_time);
    sum_times += total_time;
  }
  printf("Media: %f\n\n", sum_times/num_testes);
}

void testar_desempenho_object(char* nome, int num_testes, void (*foo)(MyClass)) {
  double time_start, time_stop, total_time, sum_times;
  sum_times = 0;
  srand ( time(NULL) );
  printf("Teste - %s\n", nome);
  for (int i = 0; i < num_testes; ++i) {
    time_start = (double)clock()/CLOCKS_PER_SEC;
    MyClass obj(true,12345);
    foo(obj);
    time_stop = (double)clock()/CLOCKS_PER_SEC;
    total_time = 1000*(time_stop - time_start);
    printf("%d/%d = %f\n", i+1, num_testes, total_time);
    sum_times += total_time;
  }
  printf("Media: %f\n\n", sum_times/num_testes);
}

int main() {
  int num_testes = 5;
  
  printf("\nTestes de Desempenho:\n");
  testar_desempenho("Inside For Variable", num_testes, &variable_inside_for);
  testar_desempenho("Outside For Variable", num_testes, &variable_outside_for);
  testar_desempenho_variable("Outside Function Variable", num_testes, &variable_outside_function);
  testar_desempenho("Variable Global", num_testes, &variable_global);
  
  testar_desempenho("Inside For Object", num_testes, &object_inside_for);
  testar_desempenho("Outside For Object", num_testes, &object_outside_for);
  testar_desempenho_object("Outside Function Object", num_testes, &object_outside_function);
  testar_desempenho("Object Global", num_testes, &object_global);

  getch();
  return 0;
}
