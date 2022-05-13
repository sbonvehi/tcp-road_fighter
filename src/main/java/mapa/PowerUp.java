package mapa;

import java.util.concurrent.TimeUnit;

import auto.Auto;

public class PowerUp extends ElementoMapa {

	
	public PowerUp(int x, int y) {
		super();
		this.ubicacion.setX(x);
		this.ubicacion.setY(y);
	}	
	
	@Override
	public void accion(Auto auto) throws InterruptedException {
		auto.aumentarVelocidadPowerUp();
		
		TimeUnit.SECONDS.sleep(1);
		
		auto.reducirVelocidadPowerUp();
		
	}

	{
		import java.util.Timer;
		import java.util.TimerTask;

		public class RepeatedTimer {
		    public static void main(String[] args) {
		        Timer timer = new Timer();
		        System.out.println("Stop Watch Started.");
		        timer.scheduleAtFixedRate(new RepeatedTask(), 500, 1000);
		    }

		    static class RepeatedTask extends TimerTask {
		        public void run() {
		            System.out.println("Running!");
		        }
		    }
		}
	}
	Hay que usar uno de estos:
		
		void schedule(TimerTask task, long delay)

		La tarea se ejecutará dentro de un tiempo.
	
	
}
