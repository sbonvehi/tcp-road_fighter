package road_fighter.utils;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Colisionador;
import road_fighter.interfaces.Renderizable;

public class GameObjectBuilder {

	private static GameObjectBuilder instance = null;

	private Group rootNode = null;

	private List<GameObject> allGameObjects = new ArrayList<GameObject>();
	private List<Actualizable> updatables = new ArrayList<Actualizable>();
	private List<Renderizable> renderables = new ArrayList<Renderizable>();
	private List<Colisionable> collideables = new ArrayList<Colisionable>();
	private List<Colisionador> collidators = new ArrayList<Colisionador>();

	private Group objectsGroup = new Group();
	private Group collidersGroup = new Group();

	private GameObjectBuilder() {

	}

	public void setRootNode(Group rootNode) {
		if (this.rootNode == null) {
			this.rootNode = rootNode;
			rootNode.getChildren().add(objectsGroup);
		} else {
			throw new RuntimeException("Root node already configured");
		}
	}

	public static GameObjectBuilder getInstance() {
		if (instance == null) {
			instance = new GameObjectBuilder();
		}
		return instance;
	}

	public void add(GameObject... gameObjects) {
		checkRootNode();

		for (GameObject gameObject : gameObjects) {
			allGameObjects.add(gameObject);

			if (Actualizable.class.isAssignableFrom(gameObject.getClass())) {
				updatables.add((Actualizable) gameObject);
			}

			if (Renderizable.class.isAssignableFrom(gameObject.getClass())) {
				Renderizable renderableGameObject = (Renderizable) gameObject;
				renderables.add(renderableGameObject);

				objectsGroup.getChildren().add(renderableGameObject.getRender());
			}

			if (Colisionador.class.isAssignableFrom(gameObject.getClass())) {
				Colisionador collidatorGameObject = (Colisionador) gameObject;
				collidators.add(collidatorGameObject);

				collidersGroup.getChildren().add(collidatorGameObject.getCollider());
			} else if (Colisionable.class.isAssignableFrom(gameObject.getClass())) {
				Colisionable collideableGameObject = (Colisionable) gameObject;
				collideables.add(collideableGameObject);

				collidersGroup.getChildren().add(collideableGameObject.getCollider());
			}
		}
	}

	public void remove(GameObject... gameObjects) {
		for (GameObject gameObject : gameObjects) {
			allGameObjects.remove(gameObject);

			if (Actualizable.class.isAssignableFrom(gameObject.getClass())) {
				updatables.remove((Actualizable) gameObject);
			}

			if (Renderizable.class.isAssignableFrom(gameObject.getClass())) {
				Renderizable renderableGameObject = (Renderizable) gameObject;
				renderables.remove(renderableGameObject);

				objectsGroup.getChildren().remove(renderableGameObject.getRender());
			}

			if (Colisionador.class.isAssignableFrom(gameObject.getClass())) {
				Colisionador collidatorGameObject = (Colisionador) gameObject;
				collidators.remove(collidatorGameObject);

				collidersGroup.getChildren().remove(collidatorGameObject.getCollider());
			} else if (Colisionable.class.isAssignableFrom(gameObject.getClass())) {
				Colisionable collideableGameObject = (Colisionable) gameObject;
				collideables.remove(collideableGameObject);

				collidersGroup.getChildren().remove(collideableGameObject.getCollider());
			}

			gameObject.destroy();
		}
	}

	public List<Actualizable> getUpdatables() {
		return new ArrayList<Actualizable>(updatables);
	}

	public List<Colisionable> getCollideables() {
		return new ArrayList<Colisionable>(collideables);
	}

	public List<Colisionador> getCollidators() {
		return new ArrayList<Colisionador>(collidators);
	}

	public void removeAll() {
		GameObject[] arrayGameObjects = allGameObjects.toArray(new GameObject[allGameObjects.size()]);
		remove(arrayGameObjects);
		this.rootNode = null;
	}

	private void checkRootNode() {
		if (rootNode == null) {
			throw new RuntimeException("Root node not configured. Please add it first");
		}
	}

}
