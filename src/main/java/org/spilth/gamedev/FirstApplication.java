package org.spilth.gamedev;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;

import static java.lang.String.format;

public class FirstApplication extends SimpleApplication {
  public static void main(String[] args) {
    AppSettings appSettings = new AppSettings(true);
    appSettings.setTitle("First!");

    FirstApplication firstApplication = new FirstApplication();
    firstApplication.setSettings(appSettings);
    firstApplication.start();
  }

  @Override
  public void simpleInitApp() {
    cam.setParallelProjection(true);
    cam.setLocation(new Vector3f(0, 0, 0.5f));
    getFlyByCamera().setEnabled(false);

    setDisplayStatView(false);
    setDisplayFps(false);

    Spatial player = getSpatial("Player");
    player.setUserData("alive", true);
    player.move(settings.getWidth() / 2, settings.getHeight() / 2, 0);
    guiNode.attachChild(player);
  }

  private Spatial getSpatial(String name) {
    Node node = new Node(name);
    Picture picture = new Picture(name);
    Texture2D texture2D = (Texture2D) assetManager.loadTexture(format("Textures/%s.png", name));
    picture.setTexture(assetManager, texture2D, true);

    float width = texture2D.getImage().getWidth();
    float height = texture2D.getImage().getHeight();
    picture.setWidth(width);
    picture.setHeight(height);
    picture.move(-width / 2f, -height / 2f, 0);

    Material material = new Material(assetManager, "Common/MatDefs/Gui/Gui.j3md");
    material.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.AlphaAdditive);
    node.setMaterial(material);
    node.setUserData("radius", width / 2);
    node.attachChild(picture);

    return node;
  }
}
