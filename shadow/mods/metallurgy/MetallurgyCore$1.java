package shadow.mods.metallurgy;

import java.awt.Component;
import javax.swing.JOptionPane;

final class MetallurgyCore$1 implements Runnable
{
    final String val$s;

    MetallurgyCore$1(String var1)
    {
        this.val$s = var1;
    }

    public void run()
    {
        JOptionPane.showMessageDialog((Component)null, "Metallurgy Error: There was a block ID conflict, you can fix this by changing the IDs in your config files!\n\n" + this.val$s);
    }
}
