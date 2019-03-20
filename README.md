# ViNiCalendarView




### Calendário em desenvolvimento para facilitar a marcação de eventos.
Versão atual __(1.0.1)__ - Possui todas as funcionalidades básicas, já está pronta para uso.

#### Instruções de implementação:</h4>

##### build.gradle(Project)</h5>

	allprojects {  
		repositories{  
    		maven { url 'https://jitpack.io' }  
  		}  
	}  

##### build.gradle(app)

	dependencies{  
		implementation 'com.github.vini2001:ViNiCalendarView:1.0.1'  
	}  


##### Código xml:

	<br.vinic.calendarview.ViNiCalendarView
       	android:id="@+id/calendarView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" 
	app:busyDayLevel1color="#ffa8a8"
        app:busyDayLevel2color="#ff6363"
        app:busyDayLevel3color="#ff0000"
	/>
	
	Personalize busyDayLevel1color, busyDayLevel2color e busyDayLevel3color como quiser. Estas são as cores das marcações no calendário de acordo com sua importância.
	
	
##### Métodos:

	void setCurrentDate(Calendar calendar); //Seta o mês e o ano que será exibido no calendário inicialmente



##### Personalização

	void setCustomBackgroundColor(int color); //Seta a cor que ficará de background no calendário (Padrão: colorPrimary)

	void setCustomSelectedDayColor(int color); //Seta a cor que ficará o dia que estiver selecionado (Padrão: Branco)

	void setCustomTextDayColor(int color); //Seta a cor que ficará os dias no calendário (Padrão: Branco)

	void setCustomWeekDayColor(int color); //Seta a cor que ficará as siglas dos dias da semana (Padrão: Branco)

	void setCustomMonthYearColor(int color); //Seta a cor que ficará o ano e o mês que se localizam no header do calendário

	void setBackgroundTransicao(Drawable background); //Seta um pequeno background que se localiza logo abaixo do calendário (Padrão: gradient da cor primária para branco)



##### Listeners

	void setOnDaySelectListener(OnDayChangeListener onDayChangeListener); //Seta o listener para quando o usuário selecionar um dia no calendário

	void setOnMonthChangeListener(OnMonthChangeListener onMonthChangeListener) //Seta o listener para quando o usuário rolar para o mês do lado

<div>
<img width="270px" height="480" src="https://raw.githubusercontent.com/vini2001/ViNiCalendarView/master/Screenshot_20181209-181920.png"/>
<img width="270px" height="480" src="https://raw.githubusercontent.com/vini2001/ViNiCalendarView/master/Screenshot_20181209-181915.png"/>
</div>


