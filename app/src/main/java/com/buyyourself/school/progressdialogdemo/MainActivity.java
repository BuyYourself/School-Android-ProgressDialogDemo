package com.buyyourself.school.progressdialogdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    Button button1, button2, button3, button4, button5, button6, button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos nuestros botones para poder trabajar con ellos
        button1 = (Button) findViewById(R.id.boton1);
        button2 = (Button) findViewById(R.id.boton2);
        button3 = (Button) findViewById(R.id.boton3);
        button4 = (Button) findViewById(R.id.boton4);
        button5 = (Button) findViewById(R.id.boton5);
        button6 = (Button) findViewById(R.id.boton6);
        button7 = (Button) findViewById(R.id.boton7);

        // Por defecto
        // La manera más rápida de mostrar un diálogo de carga
        button1.setText("Por defecto");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Los parámetros necesarios son:
                // 1) Contexto. Es decir, dónde se va a mostrar. En el caso de lanzarse desde un fragment el contexto
                // es la Activity contenedora.
                // 2) El título del diálogo de carga. Se puede no mostrar título asignándole el valor "".
                // 3) El mensaje del diálogo de carga. Se puede no mostrar mensaje asignándole el valor "".
                // 4) Este booleando indica si queremos que se muestre la carga de manera indeterminada o no.
                //    Iniciando de esta manera el díalogo de carga se muestra un spinner, que es indeterminado de por sí.
                // 5) Este booleano indice si queremos que el usuario pueda cancelar el diálogo de carga, es decir, hacer
                // que desaparezca antes de que termine. ¡Es importante controlar que no se pueda quedar el díalogo de carga
                // indefinidiamente!
                progressDialog = ProgressDialog.show(MainActivity.this, "Título", "Mensaje", false, true);
                showAndDismissProgressDialog();
            }
        });

        // Si queremos personalizar más nuestro proceso de carga podemos hacerlo de la siguiente manera,
        // creando el objeto ProgressDialog y añadiéndole las características personalizadas que consideremos.
        // Vamos a ver algunos ejemplos con un pequeño muestrario para descubrir algunas de sus características.

        button2.setText("Estilo barra horizontal");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Para inicializar el objeto ProgressDialog debemos indicarle el contexto, como se ha explicado anteriormente.
                progressDialog = new ProgressDialog(MainActivity.this);
                // El título a mostrar.
                progressDialog.setTitle("Título");
                // El mensaje a mostrar.
                progressDialog.setMessage("Mensaje");
                // El tipo de progreso. En este caso vamos a ver cómo es un progreso mediante barra horizontal.
                // En futuras entradas veremos cómo actualizar el valor numérico de carga mostrado
                // de manera que podamos saber el estado de la carga o descarga en todo momento.
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // ¡Paso importante! No te olvides de mostrar tu creación.
                progressDialog.show();
                // Utilizaremos este método para simular una carga de 5 segundos para poder visualizar el diálogo de carga.
                showAndDismissProgressDialog();
            }
        });

        button3.setText("Estilo spinner con icono");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Título");
                progressDialog.setMessage("Mensaje");
                // En este caso mostraremos un proceso de carga con estilo spinner, el utilizado por defecto.
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                // Para personalizarlo, añadiremos un icono que aparecerá a la izquierda del título.
                progressDialog.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_by));
                progressDialog.show();
                showAndDismissProgressDialog();
            }
        });

        button4.setText("Estilo barra horizontal con progreso y máximo");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // Si hemos indicado que el estilo sea de barra horizontal, podemos asignar valores numéricos a la barra de carga.
                // En este caso indicamos el progreso actual y el progreso numérico máximo posible a mostrar.
                // Por defecto los valores son 0 y 100 respectivamente.
                // En futuras entradas veremos cómo actualizar el valor numérico de carga mostrado
                // de manera que podamos saber el estado de la carga o descarga en todo momento.
                progressDialog.setSecondaryProgress(50);
                progressDialog.setMax(150);
                progressDialog.show();
                showAndDismissProgressDialog();
            }
        });

        button5.setText("Estilo barra horizontal indeterminado con icono");
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_by));
                progressDialog.setTitle("Título");
                progressDialog.setMessage("Mensaje");
                // Si indicamos que es indeterminado, podemos quitar del progreso de carga los indicadores
                // numéricos de porcentaje de la carga y progreso, de manera que se muestre más coherente.
                // Además, con la propiedad '.setIndeterminateDrawable(Drawable)' podemos personalizar
                // la animación de carga indeterminada.
                progressDialog.setIndeterminate(true);
                progressDialog.setProgressNumberFormat(null);
                progressDialog.setProgressPercentFormat(null);
                progressDialog.show();
                showAndDismissProgressDialog();
            }
        });

        button6.setText("Estilo no cancelable indeterminado con botón");
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("Título");
                progressDialog.setMessage("Mensaje");
                progressDialog.setIndeterminate(true);
                // Con esta propiedad podemos controlar que el usuario pueda cancelar o no el díalogo de carga.
                // Como se ha dicho anteriormente, ¡es importante controlar que no quede el diálogo de carga
                // mostrándose permanentemente!
                progressDialog.setCancelable(false);
                // Añadimos un botón al progreso de carga.
                // Los parámetros necesarios son:
                // 1) El botón. Tenemos 3 opciones: botón positivo, negativo y neutras. Cada uno ocupa una posición en el diálogo.
                // 2) Texto del botón.
                // 3) OnClickListener o qué hacer al pulsar el botón. En nuestro caso cancelamos el diálogo y mostramos un aviso.
                progressDialog.setButton(ProgressDialog.BUTTON_POSITIVE, "CERRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.show();
                showAndDismissProgressDialog();
            }
        });

        button7.setText("Estilo con tres botones");
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Título");
                progressDialog.setMessage("Mensaje");
                // Añadimos al progreso de carga 3 botones, como se ha explicado en el botón anterior.
                progressDialog.setButton(ProgressDialog.BUTTON_POSITIVE, "POSITIVO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Positivo pulsado", Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.setButton(ProgressDialog.BUTTON_NEUTRAL, "NEUTRAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Neutral pulsado", Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "NEGATIVO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Negativo pulsado", Toast.LENGTH_SHORT).show();
                    }
                });

                progressDialog.show();
                showAndDismissProgressDialog();
            }
        });
    }

    // Método utilizado para simular un proceso de carga.
    // En la práctica, este método será sustituido por el servicio de recuperación de información o
    // o cualquier llamada que pueda desembocar en un proceso de espera para el usuario.
    private void showAndDismissProgressDialog(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // IMPORTANTE:
                // Una vez terminado el tiempo de espera es imprescindible hacer desaparecer el proceso de carga
                if(progressDialog != null) progressDialog.dismiss();
            }
        }, 5000);
    }
}
