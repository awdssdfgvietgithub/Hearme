package com.app.hearme.view.fragments.accountssetup

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentSetFingerprintBinding
import java.util.concurrent.Executor

class SetFingerprintFragment : Fragment() {
    private lateinit var binding: FragmentSetFingerprintBinding
    private lateinit var mainActivity: MainActivity

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: PromptInfo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_set_fingerprint,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnContinue.isEnabled = false
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)
        checkDeviceHasBiometric()

        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt = BiometricPrompt(requireActivity(),
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Log.v(TAG, "Authentication error: $errString | code: $errorCode")
                    binding.btnContinue.isEnabled = false
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    mainActivity.showSnack(requireView(), "Authentication succeeded!")
                    Log.v(TAG, "Authentication succeeded: $result")
                    binding.btnContinue.isEnabled = true
                    binding.imageViewFingerPrint.setMinAndMaxProgress(0.5f, 1.0f)
                    binding.imageViewFingerPrint.playAnimation()
                    binding.imageViewFingerPrint.isEnabled = false
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    mainActivity.showSnack(requireView(), "Authentication failed!")
                    Log.v(TAG, "Authentication failed")
                    binding.btnContinue.isEnabled = false
                }
            })

        promptInfo =
            PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setNegativeButtonText("Cancel")
                .build()

        binding.imageViewFingerPrint.setOnClickListener() {
            biometricPrompt.authenticate(promptInfo)
        }

        binding.btnSkip.setOnClickListener() {
            findNavController().navigate(R.id.action_setFingerprintFragment_to_followArtistsFragment)
        }

        binding.btnContinue.setOnClickListener() {
            findNavController().navigate(R.id.action_setFingerprintFragment_to_followArtistsFragment)
        }

        mainActivity.customToolbar(
            "VISIBLE", "Set Your Fingerprint", null, R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }

    private fun checkDeviceHasBiometric() {
        val biometricManager = BiometricManager.from(requireContext())
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                mainActivity.showSnack(requireView(), "No biometric feature on this device")
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                mainActivity.showSnack(requireView(), "Biometric feature are currently unavailable")
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                mainActivity.showSnack(requireView(), "Device is not enable biometric feature")
            }
            else -> {
                mainActivity.showSnack(requireView(), "Something went wrong!")
            }
        }
    }
}