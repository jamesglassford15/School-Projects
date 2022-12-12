import tkinter as tk


class DemoGui:
   """ demonstration of event handling using bind() """
   # some class constants for calculator
   INITIAL_X = 0.0
   INITIAL_Y = 0.0

   # constructor
   def __init__(self, mstr_rt=None):

      # define two numbers that can be overwritten by user
      self.x_val = self.INITIAL_X
      self.y_val = self.INITIAL_Y

      # -------------- store root reference locally --------------
      if not self.set_root(mstr_rt):
         stand_in = tk.Tk()
         self.set_root(stand_in)

      # -------------- one message widget ------------------------
      header = "Enter your numbers and I'll add them."
      self.msg_head = tk.Message(self.root, text=header)
      self.msg_head.config(font=("times", 12, "bold"),
                           bg="lightgreen", width=300)

      # ----------------- some label widgets ------------------
      self.lab_num_1 = tk.Label(self.root, text="First Number:",
                                padx=20, pady=10)
      self.lab_num_2 = tk.Label(self.root, text="Second Number:",
                                padx=20, pady=10)
      self.lab_txt_answer = tk.Label(self.root, text="Answer:",
                                     padx=20, pady=10)
      self.lab_num_answer = tk.Label(self.root, text=" - ",
                                     padx=20, pady=10)

      # ----------------- some entry widgets ------------------
      self.enter_1 = tk.Entry(self.root)
      self.enter_1.insert(0, str(self.x_val))
      self.enter_2 = tk.Entry(self.root)
      self.enter_2.insert(0, str(self.y_val))

      # ----------------- some button widgets ------------------
      self.but_quit = tk.Button(self.root, text="Quit",
                                command=self.end_my_gui)
      self.but_add = tk.Button(self.root, text="Add")
      self.but_add.config(command=self.update_answer)

      # ------------ place all widgets using grid layout -------------
      self.msg_head.grid(row=0, column=0, columnspan=2, sticky=tk.EW)

      self.lab_num_1.grid(row=1, column=0, sticky=tk.E)
      self.lab_num_2.grid(row=2, column=0, sticky=tk.E)
      self.lab_txt_answer.grid(row=3, column=0, pady=4, sticky=tk.E)

      self.enter_1.grid(row=1, column=1, padx=5, sticky=tk.W)
      self.enter_2.grid(row=2, column=1, padx=5, sticky=tk.W)
      self.lab_num_answer.grid(row=3, column=1, sticky=tk.W)

      self.but_add.grid(row=4, column=0, padx=5, sticky=tk.EW)
      self.but_quit.grid(row=4, column=1, padx=5, sticky=tk.EW)

      # ------ update the answer using init values for x and y ---
      self.update_answer()

   # mutators
   def set_root(self, rt):
      if DemoGui.valid_tk_root(rt):
         self.root = rt
         return True
      # else
      return False

   def set_title(self, title):
      if type(title) == str:
         self.root.title = title
         return True
      # else
      return False

   # accessor
   def get_root(self):
      return self.root

   # static helper
   @staticmethod
   def valid_tk_root(am_i_a_root):
      if type(am_i_a_root) == tk.Tk:
         return True
      else:
         return False

   # event handler
   def update_answer(self, dummy_for_bind=None):
      """ second parameter required since bind passes two params
        but we don't use it -- other techniques (lambda functions)
        can be used, instead """

      self.x_val = float(self.enter_1.get())
      self.y_val = float(self.enter_2.get())
      self.lab_num_answer.config(text=str(self.x_val + self.y_val))

   # event handler for quit
   def end_my_gui(self):
      self.root.destroy()  # close the window
      # sys.exit()


# client program  -----------------------------------------

root_win = tk.Tk()
demo_cls_ref = DemoGui(root_win)
demo_cls_ref.get_root().title("Adder GUI")
demo_cls_ref.get_root().mainloop()