/**
 * 
 */
package plugins.danyfel80.vtktest;

import plugins.adufour.ezplug.EzPlug;
import plugins.adufour.ezplug.EzVarSequence;

/**
 * VTK test class that uses an overlay to show 3d lines
 * 
 * @author Daniel Felipe Gonzalez Obando
 */
public class VTKTest extends EzPlug {

  private EzVarSequence inSequence;

  /*
   * (non-Javadoc)
   * @see plugins.adufour.ezplug.EzPlug#initialize()
   */
  @Override
  protected void initialize() {
    inSequence = new EzVarSequence("Sequence");
    addEzComponent(inSequence);
  }

  /*
   * (non-Javadoc)
   * @see plugins.adufour.ezplug.EzPlug#execute()
   */
  @Override
  protected void execute() {
    if (inSequence.getValue() != null) {
      inSequence.getValue().addOverlay(new TestOverlay("Lines"));
    }
  }

  /*
   * (non-Javadoc)
   * @see plugins.adufour.ezplug.EzPlug#clean()
   */
  @Override
  public void clean() {}
}
