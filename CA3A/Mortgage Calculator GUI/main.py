import tkinter as tk

# BEGIN CLASS MortgageData --------------------------------------
import locale

locale.setlocale(locale.LC_ALL, '')


class MortgageData:
   # class ("static") intended constants
   MIN_LOAN = 1
   MAX_LOAN = 100000000
   MIN_RATE = 0.1
   MAX_RATE = 25.0
   MIN_YRS = 0.8
   MAX_YRS = 100
   MIN_STRING_LENGTH = 1
   MAX_STRING_LENGTH = 50
   ORIGINAL_DEFAULT_PRINC = 100000
   ORIGINAL_DEFAULT_RATE = 4
   ORIGINAL_DEFAULT_YEARS = 30

   # class attributes that will change over time
   default_principal = ORIGINAL_DEFAULT_PRINC
   default_rate = ORIGINAL_DEFAULT_RATE
   default_years = ORIGINAL_DEFAULT_YEARS

   # initializer ("constructor") method -------------------------------
   def __init__(self,
                principal=None,
                rate=None,
                years=None):

      # repair mutable defaults
      if (principal == None):
         principal = self.default_principal
      if (rate == None):
         rate = self.default_rate
      if (years == None):
         years = self.default_years

      # instance attributes
      if (not self.set_principal(principal)):
         self.principal = self.default_principal
      if (not self.set_rate(rate)):
         self.rate = self.default_rate
      if (not self.set_years(years)):
         self.years = self.default_years

      # make sure payment is compatible with three inputs
      self.payment = self.compute_payment()

   # mutators -----------------------------------------------
   def set_principal(self, principal):
      if not self.valid_principal(principal):
         return False
      # else
      self.principal = principal
      return True

   def set_rate(self, rate):
      if not self.valid_rate(rate):
         return False
      # else
      self.rate = rate
      return True

   def set_years(self, years):
      if not self.valid_years(years):
         return False
      # else
      self.years = years
      return True

   def clear(self):
      """ reload defaults """
      self.principal = self.default_principal
      self.rate = self.default_rate
      self.years = self.default_years
      return True

   # accessors -----------------------------------------------
   def get_principal(self):
      return self.principal

   def get_rate(self):
      return self.rate

   def get_years(self):
      return self.years

   def get_payment(self):
      return self.payment

   # instance helpers -------------------------------
   def __str__(self):
      separator = " --- "
      loan_nice = locale.currency(self.principal, grouping=True)

      ret_str = ((separator
                  + "\n    loan amount: {}"
                  + "\n    annual rate: {:8.2f}%"
                  + "\n    duration: {} years.").
                 format(loan_nice, self.rate, self.years))
      return ret_str

   # static and class helpers -------------------------------
   @classmethod
   def valid_string(cls, the_str):
      if (type(the_str) != str
         or
         not
         (cls.MIN_STRING_LENGTH <= len(the_str)
          <= cls.MAX_STRING_LENGTH)):
         return False
      # else
      return True

   @classmethod
   def valid_principal(cls, prin_in):
      if (
         (type(prin_in) != float and type(prin_in) != int)
         or
         not (cls.MIN_LOAN <= prin_in <= cls.MAX_LOAN)
      ):
         return False
      # else)
      return True

   @classmethod
   def valid_rate(cls, rate_in):
      if (
         (type(rate_in) != float and type(rate_in) != int)
         or
         not (cls.MIN_RATE <= rate_in <= cls.MAX_RATE)
      ):
         return False
      # else
      return True

   @classmethod
   def valid_years(cls, yrs_in):
      if (
         (type(yrs_in) != float and type(yrs_in) != int)
         or
         not (cls.MIN_YRS <= yrs_in <= cls.MAX_YRS)
      ):
         return False
      # else
      return True

   # class mutators and accessors ----------------------------
   @classmethod
   def set_default_principal(cls, new_prin):
      if not cls.valid_principal(new_prin):
         return False
      # else
      cls.default_principal = new_prin
      return True

   @classmethod
   def set_default_rate(cls, new_rate):
      if not cls.valid_rate(new_rate):
         return False
      # else
      cls.default_rate = new_rate
      return True

   @classmethod
   def set_default_years(cls, new_years):
      if not cls.valid_years(new_years):
         return False
      # else
      cls.default_years = new_years
      return True

   # fundamental payment calculator method ---------------
   def compute_payment(self):
      mtg_principal, mtg_annual_rate, mtg_years \
         = \
         self.get_principal(), \
         self.get_rate(), \
         self.get_years()

      # convert years to months
      months = mtg_years * 12.

      # convert rate to decimal and months
      monthly_rate = mtg_annual_rate / (100. * 12.)

      # use formula to get result
      temp = (1 + monthly_rate) ** months
      self.payment = mtg_principal * monthly_rate * temp \
                     / (temp - 1)

      return self.payment


# END CLASS MortgageData ----------------------------------------

# BEGIN CLASS MortgageGui -----------------------------------------
class MortgageGui:
   """ Adaptation of DemoGui to Mortgage Calculator  """
   INITIAL_X = 0
   INITIAL_Y = 0

   # constructor
   def __init__(self, mstr_rt=None):
      # establish a default MortgageData object
      self.the_loan = MortgageData()
      self.x_val = self.INITIAL_X
      self.y_val = self.INITIAL_Y

      # -------------- store root reference locally --------------
      if not self.set_root(mstr_rt):
         stand_in = tk.Tk()
         self.set_root(stand_in)

      # -------------- one message widget ------------------------
      header = "Enter your loan AMOUNT, loan TERM and\ninterest RATE," \
               " \n                              and \n" \
               "I'll compute the monthly PAYMENT."
      self.msg_head = tk.Message(self.root, text=header)
      self.msg_head.config(font=("times", 12, "bold"),
                           bg="lightgreen", width=350)

      # ----------------- some label widgets ------------------
      self.lab_prin = tk.Label(self.root, text="Principal:\n"
                                               "(dollars\n{} - {})".format(
         MortgageData.MIN_LOAN, MortgageData.MAX_LOAN),
                               padx=10, pady=10)
      self.lab_num_1 = tk.Label(self.root, text="Annual Interest Rate:\n(in "
                                                "percent, but no % sign\n {} "
                                                "- {})".format(
         MortgageData.MIN_RATE, MortgageData.MAX_RATE),
                                padx=10, pady=10)
      self.lab_num_2 = tk.Label(self.root, text="Term:\n(years, fractions "
                                                "okay\n{} - {})".format(
         MortgageData.MIN_YRS, MortgageData.MAX_YRS),
                                padx=10, pady=10)
      self.lab_txt_answer = tk.Label(self.root, text="Answer:\n(Monthly "
                                                     "Payment)",
                                     padx=10, pady=10)
      self.lab_num_answer = tk.Label(self.root, text=" - ",
                                     padx=10, pady=10)
      ...

      # ----------------- some entry widgets ------------------
      self.enter_prin = tk.Entry(self.root)
      self.enter_prin.insert(0, str(self.the_loan.get_principal()))
      self.enter_1 = tk.Entry(self.root)
      self.enter_1.insert(0, str(self.x_val))
      self.enter_2 = tk.Entry(self.root)
      self.enter_2.insert(0, str(self.y_val))
      ...

      # ----------------- some button widgets ------------------
      self.but_quit = tk.Button(self.root, text="Quit",
                                command=self.end_my_gui)
      self.but_add = tk.Button(self.root, text="Compute Payment", command=
      self.update_answer, width=35)
      self.but_quit.config(width=40)

      # ------------ place all widgets using grid layout -------------
      self.msg_head.grid(row=0, column=0, columnspan=4, sticky=tk.EW)

      self.lab_prin.grid(row=1, column=0, padx=20, sticky=tk.W)
      self.enter_prin.grid(row=1, column=0, sticky=tk.E)
      self.lab_num_1.grid(row=1, column=1, padx=5, sticky=tk.W)
      self.enter_1.grid(row=1, column=1, sticky=tk.E)
      self.lab_num_2.grid(row=2, column=0, padx=10, sticky=tk.W)
      self.enter_2.grid(row=2, column=0, sticky=tk.E)
      self.lab_txt_answer.grid(row=2, column=1, padx=20, sticky=tk.W)
      self.lab_num_answer.grid(row=2, column=1, padx=10, sticky=tk.E)
      self.but_add.grid(row=3, column=0, padx=5)
      self.but_quit.grid(row=3, column=1, padx=5)

      ...

      # ------ update the answer using init values for x and y ---
      self.update_answer()

   def get_root(self):
      return self.root

   def set_root(self, new_root):
      self.root = new_root

   def update_answer(self):
      self.the_loan.set_principal(int(self.enter_prin.get()))
      self.the_loan.set_rate(float(self.enter_1.get()))
      self.the_loan.set_years(float(self.enter_2.get()))
      self.lab_num_answer.config(text=str(self.the_loan.compute_payment()))

      # event handler for quit

   def end_my_gui(self):
      self.root.destroy()  # close the window


# client program ---------------------------------------

mort_cls_ref = MortgageGui()
mort_cls_ref.get_root().title("Mortgage GUI")
mort_cls_ref.get_root().mainloop()
