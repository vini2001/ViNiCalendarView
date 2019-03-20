# ViNiCalendarView




### Calendário em desenvolvimento para facilitar a marcação de eventos.
Versão atual __(1.2.0)__ - Possui todas as funcionalidades básicas e a marcação de eventos. Pronto para uso!

#### Instruções de implementação:</h4>

##### build.gradle(Project)</h5>

	allprojects {  
		repositories{  
    		maven { url 'https://jitpack.io' }  
  		}  
	}  

##### build.gradle(app)

	dependencies{  
		implementation 'com.github.vini2001:vinicalendarview:1.1'
	}  


##### Código xml:

	<br.vinic.calendarview.ViNiCalendarView
        android:id="@+id/calendar_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:busyDayLevel3color="#ffa8a8"
        app:busyDayLevel2color="#ff6363"
        app:busyDayLevel1color="#ff0000" />

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
	
##### Marcações no Calendário
	EventDay eventDay1 = new EventDay(Calendar calendar, int importance);
	/*importance pode ser EventDay.LITTLE_IMPORTANT, EventDay.IMPORTANT ou EventDay.VERY_IMPORTANT*/
	
	void addEventDays(List<EventDay> eventDayList); //Adiciona uma lista de marcações de eventos
	void addEventDay(EventDay eventDay); //Adiciona uma marcação de evento	
	void setEventDays(List<EventDay> eventDayList); //Limpa a lista de EventDays e adiciona uma nova lista e atualiza a interface com a nova informação
	void clearEventDays(); //Limpa a lista de EventDays e atualiza a interface com a nova informação
	
	
	

<div>
<img width="270px" height="480" src="https://raw.githubusercontent.com/vini2001/ViNiCalendarView/master/Screenshot_20181209-181920.png"/>
<img width="270px" height="480" src="https://raw.githubusercontent.com/vini2001/ViNiCalendarView/master/Screenshot_20181209-181915.png"/>
</div>

