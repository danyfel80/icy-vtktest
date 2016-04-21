package plugins.danyfel80.vtktest;

import icy.painter.Overlay;
import icy.painter.VtkPainter;
import icy.vtk.VtkUtil;
import vtk.vtkActor;
import vtk.vtkCellArray;
import vtk.vtkLine;
import vtk.vtkPoints;
import vtk.vtkPolyData;
import vtk.vtkPolyDataMapper;
import vtk.vtkProp;

/**
 * Test overlay showing lines
 * 
 * @author Daniel Felipe Gonzalez Obando
 */
public class TestOverlay extends Overlay implements VtkPainter {

  private static final double[][] cube_vertices = new double[][] {
      {-10, -10, -10}, {-10, 10, -10}, {10, 10, -10}, {10, -10, -10},
      {-10, -10, 10}, {-10, 10, 10}, {10, 10, 10}, {10, -10, 10}};

  private vtkActor                linesActor;

  public TestOverlay(String name) {
    super(name);
    init();
  }

  private void init() {
    // vertex data and fast java data conversion for vertices
    final vtkPoints points = VtkUtil.getPoints(cube_vertices);

    /*
     * // associate points to line sequence final vtkPolyLine lines = new
     * vtkPolyLine(); lines.GetPointIds().SetNumberOfIds(cube_vertices.length);
     * for (int v = 0; v < cube_vertices.length; v++) {
     * lines.GetPointIds().SetId(v, v); }
     */
    // Create a cell array to store the lines in and add the lines to it
    vtkCellArray lines = new vtkCellArray();

    for (int v = 0; v < cube_vertices.length - 2; v++) {
      vtkLine line = new vtkLine();
      line.GetPointIds().SetId(0, v);
      line.GetPointIds().SetId(1, v + 1);
      lines.InsertNextCell(line);
    }

    // Create poly data
    final vtkPolyData polyData = new vtkPolyData();
    // set vertex to the poly
    polyData.SetPoints(points);
    // set lines to the poly
    polyData.SetLines(lines);

    // add actor to the renderer
    final vtkPolyDataMapper polyMapper = new vtkPolyDataMapper();
    polyMapper.SetInputData(polyData);

    linesActor = new vtkActor();
    linesActor.SetMapper(polyMapper);
  }

  /*
   * (non-Javadoc)
   * @see icy.painter.VtkPainter#getProps()
   */
  @Override
  public vtkProp[] getProps() {
    return new vtkProp[] {linesActor};
  }

}
