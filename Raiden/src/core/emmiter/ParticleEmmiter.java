package core.emmiter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.concurrent.CopyOnWriteArrayList;

import core.bean.Particle;
import core.config.Constants;
import core.config.GameConfig;
import core.util.ImageUtil;
import core.util.ThreadUtil;

public class ParticleEmmiter extends Emmiter{
	CopyOnWriteArrayList<Particle> particles = new CopyOnWriteArrayList<Particle>();
	Particle p;
	public boolean up;
	public boolean down;
	
	public ParticleEmmiter(double x,double y){
		super(x, y);
		img = ImageUtil.getImage(Constants.deafultParticle);
		new emmit().start();
		new updata().start();
		new remove().start();
		this.up = false;
		this.down  =false;
	}
	
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g; 
		AffineTransform transform =  g2d.getTransform();
//		g2d.translate(x/2, y/2);
//		g2d.rotate(Math.PI/2);
		for(Particle particle:particles){
			particle.draw(g);
		}
		g2d.setTransform(transform);
	}
	
	class emmit extends Thread{
		float max;
        float min;
        double degree = Constants.ParticleDegree;
		public void run(){
			x = x-1;
			while(GameConfig.isExit){
				if (GameConfig.isPause) {
					for (int i = 0; i < Constants.ParticleNum; i++) {
						max = Constants.ParticleTimeMax;
						min = Constants.ParticleTimeMin;
						float time = (float) (min + (max - min) * Math.random());

						max = Constants.ParticleSpeedMax;
						min = Constants.ParticleSpeedMin;
						float speed = (float) (min + (max - min) * Math.random());

						if (up) {
							speed += 5;
							time += 100;
							up = false;
						} else if (down) {
							speed = (float) speed + speed;
							down = false;
						}
						p = new Particle(img, x, y, time, speed, degree);
						particles.add(p);
						ThreadUtil.sleep(Constants.ParticleInterval);
					} 
				}
				ThreadUtil.sleep(Constants.ParticleEmmitFrequence);
			}
		}
	}
	
	class remove extends Thread{
		public void run(){
			
			while (GameConfig.isExit) {
				if (GameConfig.isPause) {
					for (Particle particle : particles) {
						if (particle.time <= 0) {
							particle.isLive = false;
							particles.remove(particle); //删除超过生命周期的粒子
						}
					} 
				}
				ThreadUtil.sleep(Constants.RepaintFrequence);
			}
		}
	}
	
	class updata extends Thread{
		public void run(){
			while (GameConfig.isExit) {
				if (GameConfig.isPause) {
					for (Particle particle : particles) {
						particle.move();
					} 
				}
				ThreadUtil.sleep(Constants.RepaintFrequence);
			}
		}
	}
	
	
	
	
}
